package com.example.app.sync;

import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import org.apache.ibatis.session.SqlSession;

public class BusinessIndexSyncer extends Syncer {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 15 + 8 + 8 + 4 + 4 + 8)B = 19065
     */
    public static final int BATCH_SIZE = 4096;

    /**
     * Mapper for source database.
     */
    private BusinessIndexMapper srcMapper = null;
    /**
     * Mapper for destination database.
     */
    private BusinessIndexMapper dstMapper = null;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     */
    public BusinessIndexSyncer(final SqlSession dstSession, final BusinessIndexMapper srcMapper) {
        setDstSession(dstSession);
        this.srcMapper = srcMapper;
        this.dstMapper = dstSession.getMapper(BusinessIndexMapper.class);
    }

    /**
     * Synchronize.
     * @param name Name of the table with structure of BusinessIndex
     * @return Number of items sychronized
     */
    public long sync(final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            BusinessIndex model = srcMapper.getById(i, name);
            if (model == null) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            ++count;
            dstMapper.insert(model, name);
            if (count % BATCH_SIZE == 0) {
                commit();
            }
        }
        if (count > 0) {
            commit();
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

}
