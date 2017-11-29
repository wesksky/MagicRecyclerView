package com.sky.magicrecyclerview.magicholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sky.magicrecyclerview.R;
import com.sky.magicrecyclerview.magicdata.Type2Data;
import com.wesksky.magicrecyclerview.BaseData;
import com.wesksky.magicrecyclerview.BaseHolder;

/**
 * Created by sky on 2017/11/27.
 */

public class TitleHolder extends BaseHolder {
    TextView title;
    View view;

    public TitleHolder(View itemView) {
        super(itemView);
        view = itemView;
        title = (TextView)itemView.findViewById(R.id.item_title_textview);
    }

    @Override
    public void bindData(BaseData baseData, int position) {
        if (baseData instanceof Type2Data) {
            Type2Data data = (Type2Data)baseData;
            if (data != null && !TextUtils.isEmpty(data.getTitle())) {
                title.setText(data.getTitle());
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_title;
    }
}
