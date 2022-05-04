package com.tlc.commons.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Abishek
 * @version 1.0
 */
public abstract class BundleListener implements org.osgi.framework.BundleListener
{
    private final Set<Long> startedBundles;
    private final Lock lock;
    private static final Logger LOGGER = LoggerFactory.getLogger(BundleListener.class);
    protected BundleListener()
    {
        this.lock = new ReentrantLock();
        this.startedBundles = new HashSet<>();
    }

    @Override
    public void bundleChanged(BundleEvent event)
    {
        final Bundle bundle = event.getBundle();
        if (bundle.getSymbolicName().startsWith("com.tlc"))
        {
            final int type = event.getType();
            LOGGER.info("Metafile listener action initiated for bundle : {}, Type : {}", bundle.getSymbolicName(), type);
            if(type == BundleEvent.STARTED)
            {
                lock.lock();
                try
                {
                    final Long bundleId = bundle.getBundleId();
                    if(!startedBundles.contains(bundleId))
                    {
                        bundleStarted(bundle);
                        startedBundles.add(bundleId);
                    }
                    else
                    {
                        LOGGER.info("Bundle started called before bundle stop : {}, Type : {}", bundle.getSymbolicName(), type);
                    }
                }
                finally
                {
                    lock.unlock();
                }
            }
            else if (type == BundleEvent.STOPPED)
            {
                lock.lock();
                try
                {
                    bundleStopped(bundle);
                    startedBundles.remove(bundle.getBundleId());
                }
                finally
                {
                    lock.unlock();
                }
            }
            else if(type == BundleEvent.STOPPING)
            {
                lock.lock();
                try
                {
                    bundleStopping(bundle);
                }
                finally
                {
                    lock.unlock();
                }
            }
        }
    }

    public void bundleStarted(final Bundle bundle)
    {

    }

    public void bundleStopped(final Bundle bundle)
    {

    }

    public void bundleStopping(final Bundle bundle)
    {

    }

}
