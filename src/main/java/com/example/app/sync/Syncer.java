package com.example.app.sync;

import com.example.app.models.Model;
import com.example.app.models.Mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public abstract class Syncer<E extends Model, M extends Mapper> {

    /**
     * Logger.
     */
    protected static final Logger LOGGER = LogManager.getLogger(Syncer.class);

    /**
     * Session for destination database.
     */
    private SqlSession dstSession = null;
    /**
     * Mapper for source database.
     */
    private M srcMapper = null;
    /**
     * Mapper for destination database.
     */
    private M dstMapper = null;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     * @param dstMapper Mapper for destination database
     */
    public Syncer(final SqlSession dstSession, final M srcMapper, final M dstMapper) {
        this.dstSession = dstSession;
        this.srcMapper = srcMapper;
        this.dstMapper = dstMapper;
    }

    /**
     * Synchronize.
     * @param name Name of the table with structure of ServiceChain
     * @return Number of items sychronized
     */
    public final long sync(final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId;) {
            List<E> models = srcMapper.getByRange(i, i + getBatchSize(), name);
            boolean success = true;
            for (E model : models) {
                if (getId(model) != i) {
                    success = false;
                    break;
                }
                ++i;
                ++count;
                dstMapper.insert(model, name);
            }
            if (!success) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            commit();
        }
        if (count > 0) {
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

    protected final void commit() {
        dstSession.commit();
        dstSession.clearCache();
    }

    /**
     * Get batch size.
     * @return batch size
     */
    public abstract long getBatchSize();

    protected abstract long getId(E model);

}
