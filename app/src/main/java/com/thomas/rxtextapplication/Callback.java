package com.thomas.rxtextapplication;

/**
 * Created by thomas on 16/6/16.
 */
public interface Callback<T> {
    void onResult(T result);
    void onError(Exception e);
}
