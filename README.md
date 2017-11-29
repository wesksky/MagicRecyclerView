# MagicRecyclerView

#### WHY USE IT

#### HOW TO USE
* In Your Application
```
Magic.init(() -> Arrays.asList(
            new Type1Data(),
            new Type2Data()
        ));
```
* Create Your Own DataType
```
public class Type1Data extends BaseData {
    ...
    @Override
    protected Class bindHolder() {
        return ImageHolder.class;
    }
    ...
}
```
* Create Your Own Holder
```
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
            ...
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image;
    }
}
```

* If You Want Send A Event From Holder
```
public class Event1 implements IMagicEvent {

}

@Override
public void bindData(BaseData baseData, int position) {
    if (baseData instanceof Type1Data) {
        ...
        imageView.setOnLongClickListener(v -> {
            Event1 event = new Event1();
            event.test = "hello";
            event.position = position;
            postEvent(event);
            return true;
        });
    }
}
```

Then Receive It
```
magicRecyclerView.setIHandleMagicEvent(this);
...
@Override
public void post(IMagicEvent event) {
    if (event instanceof Event1) {
        Event1 e = (Event1)event;
    }
}
...
```
* BTW
The demo is upload to the github. If you has any suggestions/questions, 
email: 601814590@qq.com / wesksky@gmail.com
