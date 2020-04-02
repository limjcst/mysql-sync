package com.example.app;

import com.example.app.models.PlatformIndex;
import com.example.app.models.ServiceChain;
import com.example.app.models.DSChain;
import com.example.app.models.LocalChain;
import com.example.app.models.BusinessIndex;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SyncerTest {

    static final String DST_PREFIX = "dst_";

    protected SqlSessionFactory factory = null;

    @BeforeEach
    void init() throws IOException {
        factory = Syncer.prepareSqlSessionFactory("database.xml");
        Syncer.createSchema(factory, "");
        Syncer.createSchema(factory, DST_PREFIX);
    }

    @AfterEach
    void tearDown() {
        try (SqlSession session = factory.openSession()) {
            for (String name : PlatformIndex.TABLE_NAMES) {
                session.update("test.drop", DST_PREFIX + name);
            }
            for (String name : LocalChain.TABLE_NAMES) {
                session.update("test.drop", DST_PREFIX + name);
            }
            for (String name : ServiceChain.TABLE_NAMES) {
                session.update("test.drop", DST_PREFIX + name);
            }
            for (String name : DSChain.TABLE_NAMES) {
                session.update("test.drop", DST_PREFIX + name);
            }
            for (String name : BusinessIndex.TABLE_NAMES) {
                session.update("test.drop", DST_PREFIX + name);
            }
        }
    }

    @Test
    void testSmoke() {
        Syncer.summary(factory, "");
        SyncTask task = new SyncTask(factory, factory, "", DST_PREFIX);
        task.run();
    }

}
