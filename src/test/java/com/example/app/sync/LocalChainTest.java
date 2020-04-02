package com.example.app.sync;

import com.example.app.models.LocalChain;
import com.example.app.models.LocalChainMapper;

import org.apache.ibatis.session.SqlSession;

public class LocalChainTest extends SyncTest<LocalChain, LocalChainMapper> {

    protected LocalChain createModel(final long no) {
        LocalChain model = new LocalChain();
        model.setNo(no);
        model.setStartTime(100);
        model.setElapsedTime(3);
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
