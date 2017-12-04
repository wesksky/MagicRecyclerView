# MagicRecyclerView

#### WHY USE IT

* For some complicated recyclerView, such as tumbler, many kind of items by different data. It's hard to manage, so I want to solve all kind of data by one recyclerView.

* If you use a LinearLayout for a static layout, such as settings page. Sometimes the small screen phone cannot display as well, so we have to use recyclerView instead. If this static page is complicated by design, maybe we need to do a lot of work.

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
The demo is upload to the github. If you have any suggestions/questions.
email:
601814590@qq.com
wesksky@gmail.com
