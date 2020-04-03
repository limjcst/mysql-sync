package com.example.app;

import com.example.app.models.Mapper;
import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;
import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;
import com.example.app.models.DSChain;
import com.example.app.models.DSChainMapper;
import com.example.app.models.LocalChain;
import com.example.app.models.LocalChainMapper;
import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.exporter.HTTPServer;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.Timer;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public final class Syncer {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(Syncer.class);
    /**
     * Default frequency for synchronization in milliseconds.
     */
    private static final int DEFAULT_INTERVAL = 1000;
    /**
     * Default port to listen on.
     */
    private static final int DEFAULT_PORT = 8080;

    /**
     * Frequency for synchronization in milliseconds.
     */
    private static int interval;
    /**
     * Port to listen on.
     */
    private static int port;
    /**
     * Table name prefix for source database.
     */
    private static String srcPrefix = "";
    /**
     * Table name prefix for destination database.
     */
    private static String dstPrefix = "";

    /**
     * Configuration for general properties.
     */
    private static String configResource = "/config.properties";
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

    private static int parseInt(final Object property, final int value) {
        if (property == null) {
            return value;
        }
        try {
            return Integer.parseInt((String) property);
        } catch (java.lang.NumberFormatException e) {
            LOGGER.warn("Bad format of \"" + property + "\", int is expected");
            return value;
        }
    }

    private static String parseString(final Object property, final String value) {
        if (property == null) {
            return value;
        }
        return (String) property;
    }

    private static void initialize() throws IOException {
        DefaultExports.initialize();

        Properties properties = new Properties();
        InputStream inputStream = Syncer.class.getResourceAsStream(configResource);
        properties.load(inputStream);

        interval = parseInt(properties.get("interval"), DEFAULT_INTERVAL);
        port = parseInt(properties.get("port"), DEFAULT_PORT);
        srcPrefix = parseString(properties.get("src.prefix"), srcPrefix);
        dstPrefix = parseString(properties.get("dst.prefix"), dstPrefix);
        LOGGER.info("Properties:\ninterval=" + interval + "\nport=" + port
                    + "\nsrc.prefix=" + srcPrefix + "\ndst.prefix=" + dstPrefix);
    }

    /**
     * Prepare SqlSessionFactory.
     * @param resource Configuration resource.
     * @throws IOException Failed to read configuration resource.
     * @return Configured SqlSessionFactory.
     */
    public static SqlSessionFactory prepareSqlSessionFactory(final String resource) throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        factory.getConfiguration().addMapper(PlatformIndexMapper.class);
        factory.getConfiguration().addMapper(LocalChainMapper.class);
        factory.getConfiguration().addMapper(ServiceChainMapper.class);
        factory.getConfiguration().addMapper(DSChainMapper.class);
        factory.getConfiguration().addMapper(BusinessIndexMapper.class);

        return factory;
    }

    /**
     * Create database schema.
     * @param factory SqlSessionFactory.
     * @param prefix Prefix for table.
     */
    public static void createSchema(final SqlSessionFactory factory, final String prefix) {
        try (SqlSession session = factory.openSession()) {
            PlatformIndexMapper platformIndexMapper = session.getMapper(PlatformIndexMapper.class);
            for (String name : PlatformIndex.TABLE_NAMES) {
                platformIndexMapper.schema(prefix + name);
            }

            LocalChainMapper localChainMapper = session.getMapper(LocalChainMapper.class);
            for (String name : LocalChain.TABLE_NAMES) {
                localChainMapper.schema(prefix + name);
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

    /**
     * Count existent data.
     * @param factory SqlSessionFactory.
     * @param prefix Prefix for table.
     */
    public static void summary(final SqlSessionFactory factory, final String prefix) {
        try (SqlSession session = factory.openSession()) {
            Mapper mapper = session.getMapper(PlatformIndexMapper.class);
            for (String name : PlatformIndex.TABLE_NAMES) {
                String tableName = prefix + name;
                LOGGER.info("There are " + mapper.getCount(tableName) + " rows in table " + tableName);
            }

            mapper = session.getMapper(LocalChainMapper.class);
            for (String name : LocalChain.TABLE_NAMES) {
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
     * @throws IOException Failed to initialize, or error arises in Prometheus exporter.
     */
    public static void main(final String[] args) throws IOException {
        initialize();
        HTTPServer server = new HTTPServer(port, true);  // daemon thread

        LOGGER.info("Preparing target database schema");
        SqlSessionFactory dstFactory = prepareSqlSessionFactory(dstResource);
        createSchema(dstFactory, dstPrefix);
        LOGGER.info("Configuring source database");
        SqlSessionFactory srcFactory = prepareSqlSessionFactory(srcResource);
        LOGGER.info("Summary for source database");
        summary(srcFactory, srcPrefix);

        LOGGER.info("Ready for sync");

        Timer timer = new Timer(false);  // not a daemon
        timer.schedule(new SyncTask(srcFactory, dstFactory, srcPrefix, dstPrefix), 0, interval);
    }

}
