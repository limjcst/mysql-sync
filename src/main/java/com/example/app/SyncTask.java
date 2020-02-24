package com.example.app;

import com.example.app.models.PlatformIndex;
import com.example.app.models.PlatformIndexMapper;
import com.example.app.models.ServiceChain;
import com.example.app.models.ServiceChainMapper;
import com.example.app.models.DSChain;
import com.example.app.models.DSChainMapper;
import com.example.app.models.BusinessIndex;
import com.example.app.models.BusinessIndexMapper;

import java.util.TimerTask;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SyncTask extends TimerTask {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(SyncTask.class);

    /**
     * Session factory for source database.
     */
    private SqlSessionFactory srcFactory = null;
    /**
     * Session factory for destination database.
     */
    private SqlSessionFactory dstFactory = null;

    /**
     * SyncTask with SqlSessionFactory.
     * @param srcFactory Session factory for source database.
     * @param dstFactory Session factory for destination database.
     */
    public SyncTask(final SqlSessionFactory srcFactory, final SqlSessionFactory dstFactory) {
        this.srcFactory = srcFactory;
        this.dstFactory = dstFactory;
    }

    private long sync(final PlatformIndexMapper srcMapper, final PlatformIndexMapper dstMapper,
                      final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            PlatformIndex model = srcMapper.getById(i, name);
            if (model == null) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            ++count;
            dstMapper.insert(model, name);
        }
        if (count > 0) {
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

    private long sync(final ServiceChainMapper srcMapper, final ServiceChainMapper dstMapper,
                      final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            ServiceChain model = srcMapper.getById(i, name);
            if (model == null) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            ++count;
            dstMapper.insert(model, name);
        }
        if (count > 0) {
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

    private long sync(final DSChainMapper srcMapper, final DSChainMapper dstMapper,
                      final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            DSChain model = srcMapper.getById(i, name);
            if (model == null) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            ++count;
            dstMapper.insert(model, name);
        }
        if (count > 0) {
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

    private long sync(final BusinessIndexMapper srcMapper, final BusinessIndexMapper dstMapper,
                      final String name) {
        long latestId = srcMapper.getLatestId(name);
        long tailId = dstMapper.getLatestId(name);
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            BusinessIndex model = srcMapper.getById(i, name);
            if (model == null) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            ++count;
            dstMapper.insert(model, name);
        }
        if (count > 0) {
            LOGGER.info("Sync " + count + " for " + name);
        }
        return count;
    }

    /**
     * Call sync for each table.
     */
    public void run() {
        try (SqlSession srcSession = srcFactory.openSession();
             SqlSession dstSession = dstFactory.openSession()) {
            PlatformIndexMapper srcPIMapper = srcSession.getMapper(PlatformIndexMapper.class);
            PlatformIndexMapper dstPIMapper = dstSession.getMapper(PlatformIndexMapper.class);
            for (String name : PlatformIndex.TABLE_NAMES) {
                sync(srcPIMapper, dstPIMapper, name);
            }

            ServiceChainMapper srcSCMapper = srcSession.getMapper(ServiceChainMapper.class);
            ServiceChainMapper dstSCMapper = dstSession.getMapper(ServiceChainMapper.class);
            for (String name : ServiceChain.TABLE_NAMES) {
                sync(srcSCMapper, dstSCMapper, name);
            }

            DSChainMapper srcDCMapper = srcSession.getMapper(DSChainMapper.class);
            DSChainMapper dstDCMapper = dstSession.getMapper(DSChainMapper.class);
            for (String name : DSChain.TABLE_NAMES) {
                sync(srcDCMapper, dstDCMapper, name);
            }

            BusinessIndexMapper srcBIMapper = srcSession.getMapper(BusinessIndexMapper.class);
            BusinessIndexMapper dstBIMapper = dstSession.getMapper(BusinessIndexMapper.class);
            for (String name : BusinessIndex.TABLE_NAMES) {
                sync(srcBIMapper, dstBIMapper, name);
            }

            dstSession.commit();
        }
    }

}
