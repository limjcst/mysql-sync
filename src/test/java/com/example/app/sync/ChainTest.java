package com.example.app.sync;

import com.example.app.models.Chain;
import com.example.app.models.ChainMapper;

import org.apache.ibatis.session.SqlSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ChainTest extends SyncTest<Chain, ChainMapper> {

    @Test
    void testEqual() {
        Chain first = createModel(0);
        assertEquals(true, first.equals(first));
        assertEquals(false, first.equals(null));
        assertEquals(false, first.equals(this));

        Chain second = createModel(0);
        assertEquals(true, first.equals(second));
        assertEquals(0, first.hashCode() - second.hashCode());
        
        second.setNo(1);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setMsgTime(105);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setStartTime(101);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setElapsedTime(5);
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setSuccess("False");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setTraceId("another trace");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setId("another id");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setPid("another pid");
        assertEquals(false, first.equals(second));

        second = createModel(0);
        second.setCmdbId("another cmdb");
        assertEquals(false, first.equals(second));

        second.setCmdbId(null);
        assertEquals(false, second.equals(first));
        first.setCmdbId(null);
        assertEquals(true, second.equals(first));
    }

    protected Chain createModel(final long no) {
        Chain model = new Chain();
        model.setNo(no);
        model.setMsgTime(102);
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
