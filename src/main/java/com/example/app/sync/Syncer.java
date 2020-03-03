package com.example.app.sync;

import com.example.app.models.Model;
import com.example.app.models.Mapper;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public abstract class Syncer<E extends Model, M extends Mapper> {

    /**
     * Logger.
     */
    protected static final Logger LOGGER = LogManager.getLogger(Syncer.class);

    /**
     * Session factory for source database.
     */
    private SqlSessionFactory srcFactory = null;
    /**
     * Session factory for destination database.
     */
    private SqlSessionFactory dstFactory = null;

    /**
     * Construct method.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public Syncer(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
        this.srcFactory = srcFactory;
        this.dstFactory = dstFactory;
    }

    /**
     * Synchronize.
     * @param name Name of the table
     * @return Number of items sychronized
     */
    public final long sync(final String name) {
        long count = 0;
        long batchSize = getBatchSize();
        for (;;) {
            long update = syncBatch(name, batchSize);
            count += update;
            if (update < batchSize) {
                break;
            }
        }

        if (count > 0) {
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

    private long syncBatch(final String name, final long batchSize) {
        long count = 0;
        try (SqlSession srcSession = srcFactory.openSession();
             SqlSession dstSession = dstFactory.openSession(ExecutorType.BATCH)) {
            M srcMapper = getMapper(srcSession);
            M dstMapper = getMapper(dstSession);
            long headId = dstMapper.getLatestId(name) + 1;

            List<E> models = srcMapper.getByRange(headId, headId + batchSize, name);
            boolean success = true;
            for (E model : models) {
                if (getId(model) != headId) {
                    success = false;
                    break;
                }
                ++count;
                ++headId;
                dstMapper.insert(model, name);
            }
            dstSession.commit();
            if (!success) {
                LOGGER.warn("Failed to fetch " + name + " with id " + headId);
            }
        }
        return count;
    }

    /**
     * Get batch size.
     * @return batch size
     */
    public abstract long getBatchSize();

    protected abstract long getId(E model);

    protected abstract M getMapper(SqlSession session);

}
