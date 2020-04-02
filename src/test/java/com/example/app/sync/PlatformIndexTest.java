package com.example.app.sync;

import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;

import org.apache.ibatis.session.SqlSession;

public class PlatformIndexTest extends SyncTest<PlatformIndex, PlatformIndexMapper> {

    protected PlatformIndex createModel(final long no) {
        PlatformIndex model = new PlatformIndex();
        model.setId(no);
        model.setItemId(1000);
        model.setName("name");
        model.setTimestamp(100);
        model.setValue("value");
        model.setCmdbId("cmdb");
        return model;
    }

    protected PlatformIndexSyncer createSyncer() {
        return new PlatformIndexSyncer(factory, factory);
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
