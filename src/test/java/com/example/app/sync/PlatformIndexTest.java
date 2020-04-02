package com.example.app.sync;

import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;

import org.apache.ibatis.session.SqlSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PlatformIndexTest extends SyncTest<PlatformIndex, PlatformIndexMapper> {

    @Test
    void testEqual() {
        PlatformIndex first = createModel(0);
        assertEquals(true, first.equals(first));
        assertEquals(false, first.equals(null));
        assertEquals(false, first.equals(this));

        PlatformIndex second = createModel(0);
        assertEquals(true, first.equals(second));
        assertEquals(0, first.hashCode() - second.hashCode());
        
        second.setNo(1);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setItemId(1001);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setName("another name");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setBomcId("another bomc");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setTimestamp(101);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setValue("another value");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setCmdbId("another cmdb");
        assertEquals(false, first.equals(second));
    }

    protected PlatformIndex createModel(final long no) {
        PlatformIndex model = new PlatformIndex();
        model.setNo(no);
        model.setItemId(1000);
        model.setName("name");
        model.setBomcId("bomc");
        model.setTimestamp(100);
        model.setValue("value");
        model.setCmdbId("cmdb");
        return model;
    }

    protected PlatformIndexSyncer createSyncer() {
        return new PlatformIndexSyncer(factory, factory);
    }

    protected Class<PlatformIndexMapper> getMapperClass() {
        return PlatformIndexMapper.class;
    }

    protected PlatformIndexMapper getMapper(final SqlSession session) {
        return session.getMapper(PlatformIndexMapper.class);
    }

}
