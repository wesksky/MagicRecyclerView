package com.sky.magicrecyclerview.magicdata;

import com.sky.magicrecyclerview.magicholder.TitleHolder;
import com.wesksky.magicrecyclerview.BaseData;

/**
 * Created by sky on 2017/11/27.
 */

public class Type2Data extends BaseData {

    String title;

    @Override
    protected Class bindHolder() {
        return TitleHolder.class;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
