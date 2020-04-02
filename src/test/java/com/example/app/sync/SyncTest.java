package com.example.app.sync;

import com.example.app.models.Model;
import com.example.app.models.Mapper;

import java.io.IOException;
import java.io.Reader;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class SyncTest<E extends Model, M extends Mapper<E>> {

    static final String TABLE_NAME = "test";
    static final String DST_TABLE_NAME = "dst_test";

    protected SqlSessionFactory factory = null;

    protected static SqlSessionFactory createSqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("database.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory;
    }

    @BeforeEach
    void init() throws IOException {
        factory = createSqlSessionFactory();
        factory.getConfiguration().addMapper(getMapperClass());
        try (SqlSession session = factory.openSession()) {
            M mapper = getMapper(session);
            mapper.schema(TABLE_NAME);
            mapper.schema(DST_TABLE_NAME);
        }
    }

    @AfterEach
    void tearDown() {
        try (SqlSession session = factory.openSession()) {
            session.update("test.drop", TABLE_NAME);
            session.update("test.drop", DST_TABLE_NAME);
        }
    }

    @Test
    void testInsert() {
        try (SqlSession session = factory.openSession()) {
            M mapper = getMapper(session);
            assertEquals(0, mapper.getCount(TABLE_NAME));
            assertEquals(0, mapper.getLatestId(TABLE_NAME));

            E model = createModel(2);
            mapper.insert(model, TABLE_NAME);
            long id = model.getNo();

            assertEquals(1, mapper.getCount(TABLE_NAME));
            assertEquals(id, mapper.getLatestId(TABLE_NAME));
            List<E> results = mapper.getByRange(0, id + 1, TABLE_NAME);
            assertArrayEquals(new Object[]{model}, results.toArray());
            results = mapper.getByRange(0, id, TABLE_NAME);
            assertArrayEquals(new Object[]{}, results.toArray());
        }
    }

    @Test
    void testSync() {
        E model = createModel(1);
        long id = model.getNo();
        try (SqlSession session = factory.openSession()) {
            M mapper = getMapper(session);
            assertEquals(0, mapper.getCount(TABLE_NAME));
            assertEquals(0, mapper.getCount(DST_TABLE_NAME));

            mapper.insert(model, TABLE_NAME);
            session.commit();
            assertEquals(1, mapper.getCount(TABLE_NAME));
        }

        Syncer syncer = createSyncer();
        assertEquals(1, syncer.sync(TABLE_NAME, DST_TABLE_NAME));

        try (SqlSession session = factory.openSession()) {
            M mapper = getMapper(session);
            assertEquals(1, mapper.getCount(DST_TABLE_NAME));
            assertEquals(id, mapper.getLatestId(DST_TABLE_NAME));
            List<E> results = mapper.getByRange(0, id + 1, TABLE_NAME);
            assertArrayEquals(new Object[]{model}, results.toArray());
        }
    }

    protected abstract E createModel(long no);

    protected abstract Syncer createSyncer();

    protected abstract Class<M> getMapperClass();

    protected abstract M getMapper(SqlSession session);

}
