package com.example.app.sync;

import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;

import org.apache.ibatis.session.SqlSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ServiceChainTest extends SyncTest<ServiceChain, ServiceChainMapper> {

    @Test
    void testEqual() {
        ServiceChain first = createModel(0);
        assertEquals(true, first.equals(first));
        assertEquals(false, first.equals(null));
        assertEquals(false, first.equals(this));

        ServiceChain second = createModel(0);
        assertEquals(true, first.equals(second));
        assertEquals(0, first.hashCode() - second.hashCode());
        
        second.setNo(1);
        assertEquals(false, first.equals(second));
    
        second = createModel(0);
        second.setServiceName("another serviceName");
        assertEquals(false, first.equals(second));
        second.setServiceName(null);
        assertEquals(false, first.equals(second));
    }

    protected ServiceChain createModel(final long no) {
        ServiceChain model = new ServiceChain();
        model.setNo(no);
        model.setMsgTime(102);
        model.setStartTime(100);
        model.setElapsedTime(3);
        model.setSuccess("True");
        model.setTraceId("trace-id");
        model.setId("id");
        model.setPid("pid");
        model.setCmdbId("cmdb-id");
        model.setServiceName("serviceName");
        return model;
    }

    protected ServiceChainSyncer createSyncer() {
        return new ServiceChainSyncer(factory, factory);
    }

    protected Class<ServiceChainMapper> getMapperClass() {
        return ServiceChainMapper.class;
    }

    protected ServiceChainMapper getMapper(final SqlSession session) {
        return session.getMapper(ServiceChainMapper.class);
    }

}
