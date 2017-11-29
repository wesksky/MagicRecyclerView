package com.wesksky.magicrecyclerview;

/**
 * Created by sky on 2017/11/28.
 */

public interface IBaseHolder {
    int getLayoutId();

    void bindData(BaseData baseData, int position);
}
