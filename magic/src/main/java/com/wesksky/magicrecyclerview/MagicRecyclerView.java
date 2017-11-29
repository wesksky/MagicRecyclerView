package com.wesksky.magicrecyclerview;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

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

    public void setData(List data) {
        if (adapter == null) {
            return;
        } else {
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        }
    }

    public void refresh() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public interface IHandleMagicEvent {
        void post(IMagicEvent event);
    }

}
