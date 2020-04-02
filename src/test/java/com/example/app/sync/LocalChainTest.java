package com.example.app.sync;

import com.example.app.models.LocalChain;
import com.example.app.models.LocalChainMapper;

import org.apache.ibatis.session.SqlSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LocalChainTest extends SyncTest<LocalChain, LocalChainMapper> {

    @Test
    void testEqual() {
        LocalChain first = createModel(0);
        assertEquals(true, first.equals(first));
        assertEquals(false, first.equals(null));
        assertEquals(false, first.equals(this));

        LocalChain second = createModel(0);
        assertEquals(true, first.equals(second));
        assertEquals(0, first.hashCode() - second.hashCode());
        
        second.setNo(1);
        assertEquals(false, first.equals(second));
    
        second = createModel(0);
        second.setServiceName("another serviceName");
        assertEquals(false, first.equals(second));
    
        second = createModel(0);
        second.setDsName("another dsName");
        assertEquals(false, first.equals(second));
    }

    protected LocalChain createModel(final long no) {
        LocalChain model = new LocalChain();
        model.setNo(no);
        model.setMsgTime(102L);
        model.setStartTime(100L);
        model.setElapsedTime(3.0);
        model.setSuccess("True");
        model.setTraceId("trace-id");
        model.setId("id");
        model.setPid("pid");
        model.setCmdbId("cmdb-id");
        model.setServiceName("serviceName");
        model.setDsName("dsName");
        return model;
    }

    protected LocalChainSyncer createSyncer() {
        return new LocalChainSyncer(factory, factory);
    }

    protected Class<LocalChainMapper> getMapperClass() {
        return LocalChainMapper.class;
    }

    protected LocalChainMapper getMapper(final SqlSession session) {
        return session.getMapper(LocalChainMapper.class);
    }

}
