package com.example.app.sync;

import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;

import org.apache.ibatis.session.SqlSession;

public class ServiceChainTest extends SyncTest<ServiceChain, ServiceChainMapper> {

    protected ServiceChain createModel(final long no) {
        ServiceChain model = new ServiceChain();
        model.setNo(no);
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
