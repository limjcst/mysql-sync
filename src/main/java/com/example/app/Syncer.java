package com.example.app;

import com.example.app.models.EventMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.Timer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public final class Syncer {

    /**
     * Frequency for synchronization in milliseconds.
     */
    private static final int INTERVAL = 1000;

    /**
     * Configuration for source database.
     */
    private static String srcResource = "source.xml";
    /**
     * Configuration for destination database.
     */
    private static String dstResource = "destination.xml";

    private Syncer() {
    }

    private static SqlSessionFactory prepareSqlSessionFactory(final String resource) {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(EventMapper.class);

        return factory;
    }

    /**
     * Entrance.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        SqlSessionFactory srcFactory = prepareSqlSessionFactory(srcResource);
        SqlSessionFactory dstFactory = prepareSqlSessionFactory(dstResource);

        Timer timer = new Timer();
        timer.schedule(new SyncTask(srcFactory, dstFactory), 0, INTERVAL);
    }

}
