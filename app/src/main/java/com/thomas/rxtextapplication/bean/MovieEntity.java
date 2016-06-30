package com.thomas.rxtextapplication.bean;

import java.util.List;

/**
 * Created by thomas on 16/6/17.
 */
public class MovieEntity {

    private int count;

    private int start;

    private int total;

    private List<Subjects> subjects ;

    private String title;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setStart(int start){
        this.start = start;
    }
    public int getStart(){
        return this.start;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setSubjects(List<Subjects> subjects){
        this.subjects = subjects;
    }
    public List<Subjects> getSubjects(){
        return this.subjects;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", subjects=" + subjects +
                ", title='" + title + '\'' +
                '}';
    }
}
