package com.example.app.sync;

import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;

import org.apache.ibatis.session.SqlSession;

public class ServiceChainSyncer extends Syncer {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 8 + 7 + 63 + 63 + 63 + 15)B = 4462
     */
    public static final int BATCH_SIZE = 1024;

    /**
     * Mapper for source database.
     */
    private ServiceChainMapper srcMapper = null;
    /**
     * Mapper for destination database.
     */
    private ServiceChainMapper dstMapper = null;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     */
    public ServiceChainSyncer(final SqlSession dstSession, final ServiceChainMapper srcMapper) {
        setDstSession(dstSession);
        this.srcMapper = srcMapper;
        this.dstMapper = dstSession.getMapper(ServiceChainMapper.class);
    }

    /**
     * Synchronize.
     * @param name Name of the table with structure of ServiceChain
     * @return Number of items sychronized
     */
    public long sync(final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            ServiceChain model = srcMapper.getById(i, name);
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
