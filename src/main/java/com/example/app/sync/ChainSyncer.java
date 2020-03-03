package com.example.app.sync;

import com.example.app.models.Chain;
import com.example.app.models.ChainMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ChainSyncer extends Syncer<Chain, ChainMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 8 + 7 + 63 + 63 + 63)B = 4766
     */
    public static final int BATCH_SIZE = 1024;

    /**
     * Construct method.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public ChainSyncer(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
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
    protected long getId(final Chain model) {
        return model.getNo();
    }

    /**
     * Get mapper.
     * @param session Sql session
     * @return mapper
     */
    protected ChainMapper getMapper(final SqlSession session) {
        return session.getMapper(ChainMapper.class);
    }

}
