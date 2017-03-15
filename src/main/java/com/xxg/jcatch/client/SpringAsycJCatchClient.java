package com.xxg.jcatch.client;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;

/**
 * Created by wucao on 17/3/15.
 */
public class SpringAsycJCatchClient extends JCatchClient {

    private ThreadPoolTaskExecutor executor;

    public void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    private void asycSubmitQuietly(final Exception e) {
        executor.execute(new Runnable() {
            public void run() {
                try {
                    SpringAsycJCatchClient.super.submit(e);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

}
