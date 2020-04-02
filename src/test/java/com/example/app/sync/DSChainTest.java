package com.example.app.sync;

import com.example.app.models.DSChain;
import com.example.app.models.DSChainMapper;

import org.apache.ibatis.session.SqlSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DSChainTest extends SyncTest<DSChain, DSChainMapper> {

    @Test
    void testEqual() {
        DSChain first = createModel(0);
        assertEquals(true, first.equals(first));
        assertEquals(false, first.equals(null));
        assertEquals(false, first.equals(this));

        DSChain second = createModel(0);
        assertEquals(true, first.equals(second));
        assertEquals(0, first.hashCode() - second.hashCode());
        
        second.setNo(1);
        assertEquals(false, first.equals(second));
    
        second = createModel(0);
        second.setDsName("another dsName");
        assertEquals(false, first.equals(second));
        second.setDsName(null);
        assertEquals(false, first.equals(second));
    }

    protected DSChain createModel(final long no) {
        DSChain model = new DSChain();
        model.setNo(no);
        model.setMsgTime(102L);
        model.setStartTime(100L);
        model.setElapsedTime(3.0);
        model.setSuccess("True");
        model.setTraceId("trace-id");
        model.setId("id");
        model.setPid("pid");
        model.setCmdbId("cmdb-id");
        model.setDsName("dsName");
        return model;
    }

    protected DSChainSyncer createSyncer() {
        return new DSChainSyncer(factory, factory);
    }

    protected Class<DSChainMapper> getMapperClass() {
        return DSChainMapper.class;
    }

    protected DSChainMapper getMapper(final SqlSession session) {
        return session.getMapper(DSChainMapper.class);
    }

}
