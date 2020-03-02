package com.example.app.sync;

import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BusinessIndexSyncer extends Syncer<BusinessIndex, BusinessIndexMapper> {

    /**
     * Batch size for bulk sql operation.
     * 1MB / (8 + 15 + 8 + 8 + 4 + 4 + 8)B = 19065
     */
    public static final int BATCH_SIZE = 4096;

    /**
     * Construct method.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public BusinessIndexSyncer(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
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
    protected long getId(final BusinessIndex model) {
        return model.getId();
    }

    /**
     * Get mapper.
     * @param session Sql session
     * @return mapper
     */
    protected BusinessIndexMapper getMapper(final SqlSession session) {
        return session.getMapper(BusinessIndexMapper.class);
    }

}
