package com.sky.magicrecyclerview.magicdata;

import com.sky.magicrecyclerview.magicholder.ImageHolder;
import com.wesksky.magicrecyclerview.BaseHolder;
import com.wesksky.magicrecyclerview.IBaseData;

/**
 * Created by sky on 2017/11/27.
 */

public class Type1Data implements IBaseData {

    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Class<? extends BaseHolder> bindHolder() {
        return ImageHolder.class;
    }
}
