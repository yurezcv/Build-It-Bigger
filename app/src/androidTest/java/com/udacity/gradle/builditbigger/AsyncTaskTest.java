package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Test
    public void testVerifyAsyncTaskResponse() {
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
}