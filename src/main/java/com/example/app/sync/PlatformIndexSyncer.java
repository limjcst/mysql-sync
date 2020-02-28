package com.example.app.sync;

import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;

import org.apache.ibatis.session.SqlSession;

public class PlatformIndexSyncer extends Syncer<PlatformIndex, PlatformIndexMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 63 + 8 + 1024 + 15)B = 931
     */
    public static final int BATCH_SIZE = 256;

    /**
     * Construct method.
     * @param dstSession Session for destination database
     * @param srcMapper Mapper for source database
     */
    public PlatformIndexSyncer(final SqlSession dstSession, final PlatformIndexMapper srcMapper) {
        super(dstSession, srcMapper, dstSession.getMapper(PlatformIndexMapper.class));
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
    protected long getId(final PlatformIndex model) {
        return model.getId();
    }

}
