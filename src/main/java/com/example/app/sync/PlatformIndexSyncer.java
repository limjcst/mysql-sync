package com.example.app.sync;

import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;

import org.apache.ibatis.session.SqlSession;

public class PlatformIndexSyncer extends Syncer {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 63 + 8 + 1024 + 15)B = 931
     */
    public static final int BATCH_SIZE = 256;

    /**
     * Mapper for source database.
     */
    private PlatformIndexMapper srcMapper = null;
    /**
     * Mapper for destination database.
     */
    private PlatformIndexMapper dstMapper = null;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     */
    public PlatformIndexSyncer(final SqlSession dstSession, final PlatformIndexMapper srcMapper) {
        setDstSession(dstSession);
        this.srcMapper = srcMapper;
        this.dstMapper = dstSession.getMapper(PlatformIndexMapper.class);
    }

    /**
     * Synchronize.
     * @param name Name of the table with structure of PlatformIndex
     * @return Number of items sychronized
     */
    public long sync(final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            PlatformIndex model = srcMapper.getById(i, name);
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
