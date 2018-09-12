package com.wesksky.magicrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

/**
 * @author sky
 */

public abstract class BaseHolder extends ViewHolder implements IBaseHolder {
    Handler handler;
    protected View itemView;

    public BaseHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        handler = new Handler(Looper.getMainLooper());
    }

    public void postEvent(IMagicEvent event) {
        Message message = new Message();

        Bundle b = new Bundle();
        b.putSerializable("event", event);
        message.setData(b);

        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
