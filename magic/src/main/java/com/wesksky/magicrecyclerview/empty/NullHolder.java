package com.wesksky.magicrecyclerview.empty;

import android.view.View;
import com.sky.magic.R;
import com.wesksky.magicrecyclerview.BaseHolder;
import com.wesksky.magicrecyclerview.IBaseData;

/**
 * @author sky
 * @date 2018/6/20
 */
public class NullHolder extends BaseHolder {

    public NullHolder(View itemView) {
        super(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_null_view;
    }

    @Override
    public void bindData(IBaseData baseData, int position) {

    }
}
