package com.example.app.models;

import org.apache.ibatis.session.SqlSession;

public class BusinessIndexMapperTest extends MapperTest<BusinessIndex, BusinessIndexMapper> {

    protected BusinessIndex createModel() {
        BusinessIndex model = new BusinessIndex();
        model.setId(2);
        model.setServiceName("name");
        model.setStartTime(100);
        model.setAvgTime(3);
        model.setNum(300);
        model.setSucceeNum(299);
        model.setSucceeRate(0.99);
        return model;
    }

    protected long getId(final BusinessIndex model) {
        return model.getId();
    }

    protected Class<BusinessIndexMapper> getMapperClass() {
        return BusinessIndexMapper.class;
    }

    protected BusinessIndexMapper getMapper(final SqlSession session) {
        return session.getMapper(BusinessIndexMapper.class);
    }

}
