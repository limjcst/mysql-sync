package com.example.app.models;

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

public abstract class MapperTest<E extends Model, M extends Mapper<E>> {

    static final String TABLE_NAME = "test";

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
        }
    }

    @AfterEach
    void tearDown() {
        try (SqlSession session = factory.openSession()) {
            session.update("test.drop", TABLE_NAME);
        }
    }

    @Test
    void testUpdate() {
        try (SqlSession session = factory.openSession()) {
            M mapper = getMapper(session);
            assertEquals(0, mapper.getCount(TABLE_NAME));
            assertEquals(0, mapper.getLatestId(TABLE_NAME));

            E model = createModel();
            mapper.insert(model, TABLE_NAME);
            long id = getId(model);

            assertEquals(1, mapper.getCount(TABLE_NAME));
            assertEquals(id, mapper.getLatestId(TABLE_NAME));
            List<E> results = mapper.getByRange(0, id + 1, TABLE_NAME);
            assertArrayEquals(new Object[]{model}, results.toArray());
            results = mapper.getByRange(0, id, TABLE_NAME);
            assertArrayEquals(new Object[]{}, results.toArray());
        }
    }

    protected abstract E createModel();

    protected abstract long getId(E model);

    protected abstract Class<M> getMapperClass();

    protected abstract M getMapper(SqlSession session);

}
