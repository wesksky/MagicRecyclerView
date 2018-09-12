package com.wesksky.magicrecyclerview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.wesksky.magicrecyclerview.MagicAdapter.OnItemClickListener;
import com.wesksky.magicrecyclerview.MagicAdapter.OnItemDataBinding;
import com.wesksky.magicrecyclerview.MagicAdapter.OnItemLongClickListener;

/**
 * Created by sky on 2017/11/10.
 */

public class MagicRecyclerView extends RecyclerView {

    MagicAdapter adapter;

    IHandleMagicEvent handleMagicEvent;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null && msg.getData() != null
                && msg.getData().containsKey("event")
                && msg.getData().getSerializable("event") instanceof IMagicEvent) {
                // 分发事件
                IMagicEvent event = (IMagicEvent)msg.getData().getSerializable("event");
                if (handleMagicEvent != null) {
                    handleMagicEvent.post(event);
                }
            }
        }
    };

    public void setIHandleMagicEvent(IHandleMagicEvent handleMagicEvent) {
        this.handleMagicEvent = handleMagicEvent;
    }

    public MagicRecyclerView(Context context) {
        super(context);
        init(context);
        initAdapter();
    }

    public MagicRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAdapter();
    }

    public MagicRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        initAdapter();
    }

    private void initAdapter() {
        adapter = new MagicAdapter();
        adapter.setHandler(handler);
        this.setAdapter(adapter);
    }

    private void init(Context context) {
        // 确定每个item的排列方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                // 这里要复写一下，因为默认宽高都是wrap_content
                // 这个不复写，你点击的背景色就只充满你的内容
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.setLayoutManager(layoutManager);
    }

    private IBaseData emptyData;

    /**
     * Display this view when the list is null
     */
    public void setEmptyData(IBaseData emptyData) {
        this.emptyData = emptyData;
    }

    public void setData(List data) {
        adapter.setData(data);
        this.refresh();
    }

    public void refresh() {
        if (adapter.getData() == null || adapter.getData().isEmpty()) {
            if (emptyData != null) {
                List emptyList = new ArrayList();
                emptyList.add(emptyData);
                adapter.setData(emptyList);
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    int positionX;
    int positionY;

    boolean resizeEnable = false;

    public void setResizebleTitleEnable(boolean enable) {
        resizeEnable = enable;
    }

    Boolean isVerticalGesture = null;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!resizeEnable) {
            return super.onTouchEvent(event);
        }

        if (getChildCount() == 0 || getChildAt(0).getY() < 0) {
            return super.onTouchEvent(event);
        }
        if (isVerticalGesture != null && !isVerticalGesture) {
            if (event.getAction() == MotionEvent.ACTION_CANCEL
                || event.getAction() == MotionEvent.ACTION_UP) {
                isVerticalGesture = null;
            }
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                positionX = (int)event.getRawX();
                positionY = (int)event.getRawY();
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_CANCEL:
                resize(positionY);
                releaseTouchData();
                return true;
            case MotionEvent.ACTION_UP:
                resize(positionY);
                releaseTouchData();
                return true;
            case MotionEvent.ACTION_MOVE:
                // 首次获取事件
                if (positionX == 0) {
                    positionX = (int)event.getRawX();
                }
                if (positionY == 0) {
                    positionY = (int)event.getRawY();
                }
                int deltaX = (int)event.getRawX() - positionX;
                int deltaY = (int)event.getRawY() - positionY;
                if (deltaX != 0 || deltaY != 0) {
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        isVerticalGesture = false;
                        return super.onTouchEvent(event);
                    }
                }

                if (event.getRawY() < positionY) {
                    resize(positionY);
                    return super.onTouchEvent(event);
                } else {
                    resize((int)event.getRawY());
                    return true;
                }
            default:
                super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    private void releaseTouchData() {
        positionX = 0;
        positionY = 0;
        isVerticalGesture = null;
    }

    private void resize(int y) {
        double multi = (y - positionY) / 4000f + 1;
        if (resizeCallback != null) {
            resizeCallback.resize(multi);
        }
    }

    OnResizeCallback resizeCallback;

    public void setResizeCallback(OnResizeCallback resizeCallback) {
        this.resizeCallback = resizeCallback;
    }

    public interface OnResizeCallback {
        void resize(double multi);
    }

    public interface IHandleMagicEvent {
        void post(IMagicEvent event);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        adapter.setOnItemClickListener(listener);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        adapter.setOnItemLongClickListener(listener);
    }

    public void setOnItemDataBinding(OnItemDataBinding onItemDataBinding) {
        adapter.setOnItemDataBinding(onItemDataBinding);
    }
}
