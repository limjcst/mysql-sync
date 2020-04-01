package com.example.app.models;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class PlatformIndexMapperTest extends MapperTest {

    static SqlSessionFactory factory = null;
    static final String TABLE_NAME = "test";

    @BeforeAll
    static void initAll() throws IOException {
        factory = createSqlSessionFactory();
        factory.getConfiguration().addMapper(PlatformIndexMapper.class);
    }

    @BeforeEach
    void init() throws IOException {
        try (SqlSession session = factory.openSession()) {
            PlatformIndexMapper mapper = session.getMapper(PlatformIndexMapper.class);
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
            PlatformIndexMapper mapper = session.getMapper(PlatformIndexMapper.class);
            assertEquals(0, mapper.getCount(TABLE_NAME));
            assertEquals(0, mapper.getLatestId(TABLE_NAME));

            PlatformIndex index = new PlatformIndex();
            index.setId(2);
            index.setItemId(1000);
            index.setName("name");
            index.setTimestamp(100);
            index.setValue("value");
            index.setCmdbId("cmdb");

            mapper.insert(index, TABLE_NAME);

            assertEquals(1, mapper.getCount(TABLE_NAME));
            assertEquals(2, mapper.getLatestId(TABLE_NAME));
            List<PlatformIndex> results = mapper.getByRange(0, 101, TABLE_NAME);
            assertEquals(index, results.get(0));
            assertArrayEquals(new PlatformIndex[]{index},
                              results.toArray(new PlatformIndex[results.size()]));
            results = mapper.getByRange(0, 100, TABLE_NAME);
            assertArrayEquals(new PlatformIndex[]{index},
                              results.toArray(new PlatformIndex[results.size()]));
        }
    }

}
