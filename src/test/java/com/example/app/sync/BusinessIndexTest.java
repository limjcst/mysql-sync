package com.example.app.sync;

import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import org.apache.ibatis.session.SqlSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BusinessIndexTest extends SyncTest<BusinessIndex, BusinessIndexMapper> {

    @Test
    void testEqual() {
        BusinessIndex first = createModel(0);
        assertEquals(true, first.equals(first));
        assertEquals(false, first.equals(null));
        assertEquals(false, first.equals(this));

        BusinessIndex second = createModel(0);
        assertEquals(true, first.equals(second));
        assertEquals(0, first.hashCode() - second.hashCode());
        
        second.setNo(1);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setServiceName("another name");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setStartTime(101L);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setAvgTime(2.0);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setNum(301L);
        assertEquals(false, first.equals(second));
    
        second = createModel(0);
        second.setSucceeNum(200L);
        assertEquals(false, first.equals(second));
    
        second = createModel(0);
        second.setSucceeRate(0.6);
        assertEquals(false, first.equals(second));

        second.setSucceeRate(null);
        assertEquals(false, first.equals(second));
        first.setSucceeRate(null);
        assertEquals(true, first.equals(second));
    }

    protected BusinessIndex createModel(final long no) {
        BusinessIndex model = new BusinessIndex();
        model.setNo(no);
        model.setServiceName("name");
        model.setStartTime(100L);
        model.setAvgTime(3.0);
        model.setNum(300L);
        model.setSucceeNum(299L);
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
