package com.example.app.sync;

import org.apache.ibatis.session.SqlSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public abstract class Syncer {

    /**
     * Logger.
     */
    protected static final Logger LOGGER = LogManager.getLogger(Syncer.class);

    /**
     * Session for destination database.
     */
    private SqlSession dstSession = null;

    /**
     * Synchronize.
     * @param name Name of the table with structure of ServiceChain
     * @return Number of items sychronized
     */
    public abstract long sync(String name);

    protected final SqlSession getDstSession() {
        return dstSession;
    }

    protected final void setDstSession(final SqlSession dstSession) {
        this.dstSession = dstSession;
    }

    protected final void commit() {
        dstSession.commit();
        dstSession.clearCache();
    }

}
