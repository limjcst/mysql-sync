package com.example.app.sync;

import com.example.app.models.Chain;
import com.example.app.models.ChainMapper;

import org.apache.ibatis.session.SqlSession;

public class ChainTest extends SyncTest<Chain, ChainMapper> {

    protected Chain createModel(final long no) {
        Chain model = new Chain();
        model.setNo(no);
        model.setStartTime(100);
        model.setElapsedTime(3);
        model.setSuccess("True");
        model.setTraceId("trace-id");
        model.setId("id");
        model.setPid("pid");
        model.setCmdbId("cmdb-id");
        return model;
    }

    protected ChainSyncer createSyncer() {
        return new ChainSyncer(factory, factory);
    }

    protected Class<ChainMapper> getMapperClass() {
        return ChainMapper.class;
    }

    protected ChainMapper getMapper(final SqlSession session) {
        return session.getMapper(ChainMapper.class);
    }

}
