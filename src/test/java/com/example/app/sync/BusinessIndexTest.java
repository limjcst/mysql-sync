package com.example.app.sync;

import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import org.apache.ibatis.session.SqlSession;

public class BusinessIndexTest extends SyncTest<BusinessIndex, BusinessIndexMapper> {

    protected BusinessIndex createModel(final long no) {
        BusinessIndex model = new BusinessIndex();
        model.setNo(no);
        model.setServiceName("name");
        model.setStartTime(100);
        model.setAvgTime(3);
        model.setNum(300);
        model.setSucceeNum(299);
        model.setSucceeRate(0.99);
        return model;
    }

    protected BusinessIndexSyncer createSyncer() {
        return new BusinessIndexSyncer(factory, factory);
    }

    protected Class<BusinessIndexMapper> getMapperClass() {
        return BusinessIndexMapper.class;
    }

    protected BusinessIndexMapper getMapper(final SqlSession session) {
        return session.getMapper(BusinessIndexMapper.class);
    }

}
