package com.thomas.rxtextapplication.retrofit;

/**
 * Created by thomas on 16/6/17.
 */
public class HttpResult<T> {

    //用来模仿resultCode和resultMessage
    private int count;
    private int start;
    private int total;
    private String title;

    //用来模仿Data
    private T subjects;
}
