package com.thomas.rxtextapplication;

import android.net.Uri;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 16/6/16.
 */
public class ApiImp implements Api{
    @Override
    public void queryCats(String query, CatsQueryCallback catsQueryCallback) {

        SystemClock.sleep(2000);
        if(catsQueryCallback!=null){
            List<Cat> l= new ArrayList<>();
            l.add(new Cat(2));
            l.add(new Cat(3));
            l.add(new Cat(9));
            catsQueryCallback.onCatListReceived(l);
        }
    }

    @Override
    public void store(Cat cat, StoreCallback storeCallback) {
        SystemClock.sleep(2000);
        if(cat!=null){
            if(storeCallback!=null){
                storeCallback.onCatStored(Uri.parse("保存好了："+cat.cuteness));
            }
        }

    }
}
