package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Test
    public void testVerifyAsyncTaskViaCallback() {
        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.AsyncTaskCallback() {

            @Override
            public void onAsyncTaskBegins() {
                // do nothing in the test
            }

            @Override
            public void onAsyncTaskDone(String result) {
                assertTrue(result.length() > 0);
            }
        });
    }

    @Test
    public void testVerifyAsyncTaskViaResponse() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        EndpointsAsyncTask testTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue(result.length() > 0);
                latch.countDown();
            }
        };

        EndpointsAsyncTask.AsyncTaskCallback callback = new EndpointsAsyncTask.AsyncTaskCallback() {

            @Override
            public void onAsyncTaskBegins() {
                // do nothing for the test
            }

            @Override
            public void onAsyncTaskDone(String result) {
                // do nothing for the test
            }
        };

        testTask.execute(callback);
        latch.await();
    }
}