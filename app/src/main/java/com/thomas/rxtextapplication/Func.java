package com.thomas.rxtextapplication;

/**
 * Created by thomas on 16/6/16.
 */
public interface Func<T, R> {
    R call(T t);
}
