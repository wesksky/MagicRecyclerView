package com.sky.magicrecyclerview.magicholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.sky.magicrecyclerview.magicevent.Event1;
import com.sky.magicrecyclerview.R;
import com.sky.magicrecyclerview.magicdata.Type1Data;
import com.wesksky.magicrecyclerview.BaseData;
import com.wesksky.magicrecyclerview.BaseHolder;

/**
 * Created by sky on 2017/11/27.
 */

public class ImageHolder extends BaseHolder {
    ImageView imageView;
    View view;

    public ImageHolder(View itemView) {
        super(itemView);
        view = itemView;
        imageView = (ImageView)itemView.findViewById(R.id.item1_imageview);
    }

    @Override
    public void bindData(BaseData baseData, int position) {
        if (baseData instanceof Type1Data) {
            Type1Data data = (Type1Data)baseData;
            if (data != null && !TextUtils.isEmpty(data.getUrl())) {
                Glide.with(view.getContext())
                    .load(data.getUrl())
                    .into(imageView);
            } else {
                Glide.with(view.getContext())
                    .load("")
                    .into(imageView);
            }
            imageView.setOnClickListener(
                v -> Toast.makeText(view.getContext(), data.getUrl(), Toast.LENGTH_LONG).show());

            imageView.setOnLongClickListener(v -> {
                Event1 event = new Event1();
                event.test = "hello";
                event.position = position;
                postEvent(event);
                return true;
            });
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image;
    }
}
