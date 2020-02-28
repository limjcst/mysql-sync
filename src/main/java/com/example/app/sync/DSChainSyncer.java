package com.example.app.sync;

import com.example.app.models.DSChain;
import com.example.app.models.DSChainMapper;

import org.apache.ibatis.session.SqlSession;

public class DSChainSyncer extends Syncer<DSChain, DSChainMapper> {

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
    public DSChainSyncer(final SqlSession dstSession, final DSChainMapper srcMapper) {
        super(dstSession, srcMapper, dstSession.getMapper(DSChainMapper.class));
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
    protected long getId(final DSChain model) {
        return model.getNo();
    }

}
