package com.example.app;

import com.example.app.models.Mapper;
import com.example.app.models.Model;
import com.example.app.models.EventMapper;

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

    private long sync(final Mapper srcMapper, final Mapper dstMapper, final String name) {
        long latestId = srcMapper.getLatestId();
        long tailId = dstMapper.getLatestId();
        long count = 0;
        for (long i = tailId + 1; i <= latestId; ++i) {
            Model model = srcMapper.getById(i);
            if (model == null) {
                LOGGER.warn("Failed to fetch " + name + " with id " + i);
                break;
            }
            ++count;
            dstMapper.insert(model);
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
            sync(srcSession.getMapper(EventMapper.class),
                 dstSession.getMapper(EventMapper.class),
                 "event");
            dstSession.commit();
        }
    }

}
