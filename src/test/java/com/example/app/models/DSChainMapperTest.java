package com.example.app.models;

import org.apache.ibatis.session.SqlSession;

public class DSChainMapperTest extends MapperTest<DSChain, DSChainMapper> {

    protected DSChain createModel() {
        DSChain model = new DSChain();
        model.setNo(2);
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

    protected long getId(final DSChain model) {
        return model.getNo();
    }

    protected Class<DSChainMapper> getMapperClass() {
        return DSChainMapper.class;
    }

    protected DSChainMapper getMapper(final SqlSession session) {
        return session.getMapper(DSChainMapper.class);
    }

}
