package com.example.app;

import com.example.app.models.PlatformIndex;
import com.example.app.models.Chain;
import com.example.app.models.ServiceChain;
import com.example.app.models.DSChain;
import com.example.app.models.BusinessIndex;

import com.example.app.sync.Syncer;
import com.example.app.sync.PlatformIndexSyncer;
import com.example.app.sync.ChainSyncer;
import com.example.app.sync.ServiceChainSyncer;
import com.example.app.sync.DSChainSyncer;
import com.example.app.sync.BusinessIndexSyncer;

import java.util.TimerTask;

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

    /**
     * Call sync for each table.
     */
    public void run() {
        LOGGER.info("Start to sync");
        Syncer syncer = new PlatformIndexSyncer(srcFactory, dstFactory);
        for (String name : PlatformIndex.TABLE_NAMES) {
            syncer.sync(name);
        }

        syncer = new ChainSyncer(srcFactory, dstFactory);
        for (String name : Chain.TABLE_NAMES) {
            syncer.sync(name);
        }

        syncer = new ServiceChainSyncer(srcFactory, dstFactory);
        for (String name : ServiceChain.TABLE_NAMES) {
            syncer.sync(name);
        }

        syncer = new DSChainSyncer(srcFactory, dstFactory);
        for (String name : DSChain.TABLE_NAMES) {
            syncer.sync(name);
        }

        syncer = new BusinessIndexSyncer(srcFactory, dstFactory);
        for (String name : BusinessIndex.TABLE_NAMES) {
            syncer.sync(name);
        }
    }

}
