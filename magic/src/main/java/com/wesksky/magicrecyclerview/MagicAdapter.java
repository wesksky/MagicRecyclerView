package com.wesksky.magicrecyclerview;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import android.os.Handler;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sky on 2017/11/10.
 */

public class MagicAdapter extends MagicRecyclerView.Adapter {

    public static final int UNKNOWN_TYPE = -1;

    List<BaseData> data;

    Handler handler;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == UNKNOWN_TYPE) {
            return null;
        }
        Class baseDataClass = Magic.getInstance().getBaseDataByPosition(viewType);
        BaseData baseData;
        try {
            baseData = (BaseData)Class.forName(baseDataClass.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }

        Class holderClass = null;

        if (baseData != null) {
            holderClass = baseData.bindHolder();
        }

        if (holderClass != null) {
            try {
                Constructor constructor = holderClass.getConstructor(View.class);
                IBaseHolder baseHolderImpl = (IBaseHolder)constructor.newInstance(new View(parent.getContext()));
                if (baseHolderImpl.getLayoutId() == 0) {
                    return null;
                }
                BaseHolder baseHolder = (BaseHolder)constructor.newInstance(
                    LayoutInflater.from(parent.getContext()).inflate(baseHolderImpl.getLayoutId(), null));
                baseHolder.setHandler(handler);
                return baseHolder;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof BaseHolder) {
            BaseHolder baseHolder = (BaseHolder)holder;
            baseHolder.bindData(data.get(position), position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Magic.getInstance().getBaseDataTypeByClass(data.get(position).getClass());
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void setData(List data) {
        this.data = data;
    }

}