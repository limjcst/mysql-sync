package com.example.app.sync;

import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ServiceChainSyncer extends Syncer<ServiceChain, ServiceChainMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 8 + 7 + 63 + 63 + 63 + 20 + 20)B = 4033
     */
    public static final int BATCH_SIZE = 1024;

    /**
     * Construct method.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public ServiceChainSyncer(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
        super(srcFactory, dstFactory);
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

    /**
     * Get mapper.
     * @param session Sql session
     * @return mapper
     */
    protected ServiceChainMapper getMapper(final SqlSession session) {
        return session.getMapper(ServiceChainMapper.class);
    }

}
