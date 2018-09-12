package com.sky.magicrecyclerview;

import java.util.Arrays;

import android.app.Application;
import com.sky.magicrecyclerview.magicdata.Type1Data;
import com.sky.magicrecyclerview.magicdata.Type2Data;
import com.wesksky.magicrecyclerview.Magic;

/**
 * Created by sky on 2017/11/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Magic.init(() -> Arrays.asList(
            Type1Data.class,
            Type2Data.class
        ));
    }
}
