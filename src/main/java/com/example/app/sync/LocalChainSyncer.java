package com.example.app.sync;

import com.example.app.models.LocalChain;
import com.example.app.models.LocalChainMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class LocalChainSyncer extends Syncer<LocalChain, LocalChainMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 8 + 7 + 63 + 63 + 63 + 20 + 20 + 20)B = 3745
     */
    public static final int BATCH_SIZE = 1024;

    /**
     * Construct method.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public LocalChainSyncer(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
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
    protected long getId(final LocalChain model) {
        return model.getNo();
    }

    /**
     * Get mapper.
     * @param session Sql session
     * @return mapper
     */
    protected LocalChainMapper getMapper(final SqlSession session) {
        return session.getMapper(LocalChainMapper.class);
    }

}
