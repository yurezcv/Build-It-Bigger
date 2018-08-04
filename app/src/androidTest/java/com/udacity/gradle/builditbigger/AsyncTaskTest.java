package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Test
    public void testVerifyAsyncTaskViaResponse() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if(!result.contains("#ERROR")) {
                    assertTrue(result.length() > 0);
                    latch.countDown();
                } else {
                    fail(result);
                }
            }
        };

        EndpointsAsyncTask.AsyncTaskCallback callback = new EndpointsAsyncTask.AsyncTaskCallback() {

            @Override
            public void onAsyncTaskBegins() {
                // do nothing for the test, required only for UI changes
            }

            @Override
            public void onAsyncTaskDone(String result) {
                // do nothing for the test, required only for UI changes
            }
        };

        testTask.execute(callback);
        latch.await(10, TimeUnit.SECONDS);
    }
}