package com.sky.magicrecyclerview.magicdata;

import com.sky.magicrecyclerview.magicholder.ImageHolder;
import com.wesksky.magicrecyclerview.BaseData;

/**
 * Created by sky on 2017/11/27.
 */

public class Type1Data extends BaseData {

    String url;

    @Override
    protected Class bindHolder() {
        return ImageHolder.class;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
