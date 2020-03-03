package com.example.app;

import com.example.app.models.Mapper;
import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;
import com.example.app.models.Chain;
import com.example.app.models.ChainMapper;
import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;
import com.example.app.models.DSChain;
import com.example.app.models.DSChainMapper;
import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.Timer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class Syncer {

    /**
     * Frequency for synchronization in milliseconds.
     */
    private static final int INTERVAL = 1000;
    /**
     * Table name prefix for source database.
     */
    private static String srcPrefix = "";
    /**
     * Table name prefix for destination database.
     */
    private static String dstPrefix = "";

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(Syncer.class);

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
        factory.getConfiguration().addMapper(PlatformIndexMapper.class);
        factory.getConfiguration().addMapper(ChainMapper.class);
        factory.getConfiguration().addMapper(ServiceChainMapper.class);
        factory.getConfiguration().addMapper(DSChainMapper.class);
        factory.getConfiguration().addMapper(BusinessIndexMapper.class);

        return factory;
    }

    private static void createSchema(final SqlSessionFactory factory, final String prefix) {
        try (SqlSession session = factory.openSession()) {
            PlatformIndexMapper platformIndexMapper = session.getMapper(PlatformIndexMapper.class);
            for (String name : PlatformIndex.TABLE_NAMES) {
                platformIndexMapper.schema(prefix + name);
            }

            ChainMapper chainMapper = session.getMapper(ChainMapper.class);
            for (String name : Chain.TABLE_NAMES) {
                chainMapper.schema(prefix + name);
            }

            ServiceChainMapper serviceChainMapper = session.getMapper(ServiceChainMapper.class);
            for (String name : ServiceChain.TABLE_NAMES) {
                serviceChainMapper.schema(prefix + name);
            }

            DSChainMapper dsChainMapper = session.getMapper(DSChainMapper.class);
            for (String name : DSChain.TABLE_NAMES) {
                dsChainMapper.schema(prefix + name);
            }

            BusinessIndexMapper businessIndexMapper = session.getMapper(BusinessIndexMapper.class);
            for (String name : BusinessIndex.TABLE_NAMES) {
                businessIndexMapper.schema(prefix + name);
            }
        }
    }

    private static void summary(final SqlSessionFactory factory, final String prefix) {
        try (SqlSession session = factory.openSession()) {
            Mapper mapper = session.getMapper(PlatformIndexMapper.class);
            for (String name : PlatformIndex.TABLE_NAMES) {
                String tableName = prefix + name;
                LOGGER.info("There are " + mapper.getCount(tableName) + " rows in table " + tableName);
            }

            mapper = session.getMapper(ChainMapper.class);
            for (String name : Chain.TABLE_NAMES) {
                String tableName = prefix + name;
                LOGGER.info("There are " + mapper.getCount(tableName) + " rows in table " + tableName);
            }

            mapper = session.getMapper(ServiceChainMapper.class);
            for (String name : ServiceChain.TABLE_NAMES) {
                String tableName = prefix + name;
                LOGGER.info("There are " + mapper.getCount(tableName) + " rows in table " + tableName);
            }

            mapper = session.getMapper(DSChainMapper.class);
            for (String name : DSChain.TABLE_NAMES) {
                String tableName = prefix + name;
                LOGGER.info("There are " + mapper.getCount(tableName) + " rows in table " + tableName);
            }

            mapper = session.getMapper(BusinessIndexMapper.class);
            for (String name : BusinessIndex.TABLE_NAMES) {
                String tableName = prefix + name;
                LOGGER.info("There are " + mapper.getCount(tableName) + " rows in table " + tableName);
            }
        }
    }

    /**
     * Entrance.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        LOGGER.info("Preparing target database schema");
        SqlSessionFactory dstFactory = prepareSqlSessionFactory(dstResource);
        createSchema(dstFactory, dstPrefix);
        LOGGER.info("Configuring source database");
        SqlSessionFactory srcFactory = prepareSqlSessionFactory(srcResource);
        LOGGER.info("Summary for source database");
        summary(srcFactory, srcPrefix);

        LOGGER.info("Ready for sync");

        Timer timer = new Timer();
        timer.schedule(new SyncTask(srcFactory, dstFactory, srcPrefix, dstPrefix), 0, INTERVAL);
    }

}
