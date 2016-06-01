package com.siping.integrate.constant;

import java.util.concurrent.CountDownLatch;

public abstract class SynchronizedThread implements Runnable {

    public final CountDownLatch countDownLatch;
    
    public final Object[] args;

    public SynchronizedThread(final CountDownLatch countDownLatch, Object ... args) {
        this.countDownLatch = countDownLatch;
        this.args = args;
    }

}