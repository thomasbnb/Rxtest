package com.thomas.rxtextapplication;

import android.graphics.Bitmap;

/**
 * Created by thomas on 16/6/16.
 */
public class Cat implements Comparable<Cat>{
    int cuteness;

    public Cat(int cuteness) {
        this.cuteness = cuteness;
    }

    @Override
    public int compareTo(Cat another) {
        // 好像这样返回的结果是对的
        return cuteness-another.cuteness;
    }
}