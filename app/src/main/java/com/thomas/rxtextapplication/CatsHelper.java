package com.thomas.rxtextapplication;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * Created by thomas on 16/6/16.
 */
public class CatsHelper {

    ApiWrapper apiWrapper;

    public CatsHelper(ApiWrapper apiWrapper) {
        this.apiWrapper = apiWrapper;
    }

    public AsyncJob<Uri> saveTheCutestCat(final String query) {

        final AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        final AsyncJob<Cat> cutestCatAsyncJob =catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCutest(cats);
            }
        });
        AsyncJob<Uri> storedUriAsyncJob =  cutestCatAsyncJob.flatMap(new Func<Cat, AsyncJob<Uri>>() {
            @Override
            public AsyncJob<Uri> call(Cat cat) {
                return apiWrapper.store(cat);
            }
        });

        return storedUriAsyncJob;
    }

    private Cat findCutest(List<Cat> cats) {
        return Collections.max(cats);
    }
}
