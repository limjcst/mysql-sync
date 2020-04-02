package com.example.app.sync;

import com.example.app.models.DSChain;
import com.example.app.models.DSChainMapper;

import org.apache.ibatis.session.SqlSession;

public class DSChainTest extends SyncTest<DSChain, DSChainMapper> {

    protected DSChain createModel(final long no) {
        DSChain model = new DSChain();
        model.setNo(no);
        model.setMsgTime(102);
        model.setStartTime(100);
        model.setElapsedTime(3);
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
