package com.example.app.sync;

import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PlatformIndexSyncer extends Syncer<PlatformIndex, PlatformIndexMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 8 + 63 + 8 + 1024 + 15)B = 931
     */
    public static final int BATCH_SIZE = 256;

    /**
     * Construct method.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public PlatformIndexSyncer(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
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
     * Get mapper.
     * @param session Sql session
     * @return mapper
     */
    protected PlatformIndexMapper getMapper(final SqlSession session) {
        return session.getMapper(PlatformIndexMapper.class);
    }

}
