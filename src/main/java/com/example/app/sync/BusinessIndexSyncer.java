package com.example.app.sync;

import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import org.apache.ibatis.session.SqlSession;

public class BusinessIndexSyncer extends Syncer<BusinessIndex, BusinessIndexMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 15 + 8 + 8 + 4 + 4 + 8)B = 19065
     */
    public static final int BATCH_SIZE = 4096;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     */
    public BusinessIndexSyncer(final SqlSession dstSession, final BusinessIndexMapper srcMapper) {
        super(dstSession, srcMapper, dstSession.getMapper(BusinessIndexMapper.class));
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
    protected long getId(final BusinessIndex model) {
        return model.getId();
    }

}
