package com.sbt.SpringBeans.MBeansTest;

/**
 * Created by SBT-Klyshov-DA on 17.05.2018.
 */
public interface ServerControllerMBean{
    public int getCorePoolSize();
    public void setCorePoolSize(int corePoolSize);
    public int getMaxPoolSize();
    public void setMaxPoolSize(int maxPoolSize);
    public int getRejectedCount();
    public int getActiveThreads();
    public int getPassiveThreads();
    public int getTotalThreads();
    public void flushRejected();
}