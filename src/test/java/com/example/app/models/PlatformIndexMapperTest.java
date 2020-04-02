package com.example.app.models;

import org.apache.ibatis.session.SqlSession;

public class PlatformIndexMapperTest extends MapperTest<PlatformIndex, PlatformIndexMapper> {

    protected PlatformIndex createModel() {
        PlatformIndex model = new PlatformIndex();
        model.setId(2);
        model.setItemId(1000);
        model.setName("name");
        model.setTimestamp(100);
        model.setValue("value");
        model.setCmdbId("cmdb");
        return model;
    }

    protected long getId(final PlatformIndex model) {
        return model.getId();
    }

    protected Class<PlatformIndexMapper> getMapperClass() {
        return PlatformIndexMapper.class;
    }

    protected PlatformIndexMapper getMapper(final SqlSession session) {
        return session.getMapper(PlatformIndexMapper.class);
    }

}
