package com.sky.magicrecyclerview.magicdata;

import com.sky.magicrecyclerview.magicholder.TitleHolder;
import com.wesksky.magicrecyclerview.BaseHolder;
import com.wesksky.magicrecyclerview.IBaseData;

/**
 * Created by sky on 2017/11/27.
 */

public class Type2Data implements IBaseData {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Class<? extends BaseHolder> bindHolder() {
        return TitleHolder.class;
    }
}
