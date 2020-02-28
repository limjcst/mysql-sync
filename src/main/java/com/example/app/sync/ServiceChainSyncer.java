package com.example.app.sync;

import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;

import org.apache.ibatis.session.SqlSession;

public class ServiceChainSyncer extends Syncer<ServiceChain, ServiceChainMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 8 + 7 + 63 + 63 + 63 + 15)B = 4462
     */
    public static final int BATCH_SIZE = 1024;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     */
    public ServiceChainSyncer(final SqlSession dstSession, final ServiceChainMapper srcMapper) {
        super(dstSession, srcMapper, dstSession.getMapper(ServiceChainMapper.class));
    }

    /**
     * Get batch size.
     * @return batch size
     */
    public long getBatchSize() {
        return BATCH_SIZE;
    }

    /**
     * Get identifier of model.
     * @param model model
     * @return identifier
     */
    protected long getId(final ServiceChain model) {
        return model.getNo();
    }

}
