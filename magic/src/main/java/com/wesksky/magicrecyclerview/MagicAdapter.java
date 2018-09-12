package com.wesksky.magicrecyclerview;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import com.wesksky.magicrecyclerview.empty.NullHolder;

/**
 * @author sky
 * @date 2017/11/10
 */
public class MagicAdapter extends RecyclerView.Adapter {

    public static final int UNKNOWN_TYPE = -1;

    public static final String INIT_TAG = "init_tag";

    List<IBaseData> data;

    Handler handler;

    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;
    OnItemDataBinding onItemDataBinding;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == UNKNOWN_TYPE) {
            return generateNullHolder(parent);
        }
        Class baseDataClass = Magic.getInstance().getBaseDataByPosition(viewType);
        IBaseData baseData;
        try {
            baseData = (IBaseData)Class.forName(baseDataClass.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return generateNullHolder(parent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return generateNullHolder(parent);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return generateNullHolder(parent);
        }

        Class holderClass = null;

        if (baseData != null) {
            holderClass = baseData.bindHolder();
        }

        if (holderClass != null) {
            try {
                Constructor constructor = holderClass.getConstructor(View.class);
                View v = new View(parent.getContext());
                v.setTag(INIT_TAG);
                IBaseHolder baseHolderImpl = (IBaseHolder)constructor.newInstance(v);
                if (baseHolderImpl.getLayoutId() <= 0) {
                    return generateNullHolder(parent);
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
        return generateNullHolder(parent);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (onItemDataBinding != null) {
            onItemDataBinding.onBinding(position);
        }
        if (holder instanceof BaseHolder) {
            if (onItemClickListener != null) {
                ((BaseHolder)holder).itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(position);
                    }
                });
            } else {
                ((BaseHolder)holder).itemView.setOnClickListener(null);
            }

            if (onItemLongClickListener != null) {
                ((BaseHolder)holder).itemView.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemLongClickListener.onItemLongClick(position);
                        return true;
                    }
                });
            } else {
                ((BaseHolder)holder).itemView.setOnLongClickListener(null);
            }

            BaseHolder baseHolder = (BaseHolder)holder;
            baseHolder.bindData(data.get(position), position);
        }
    }

    private ViewHolder generateNullHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(new NullHolder(parent).getLayoutId(), null);
        return new NullHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) == null || data.get(position).getClass() == null) {
            return Magic.getInstance().getBaseDataTypeByClass(null);
        }
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

    public List getData() {
        return this.data;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public interface OnItemDataBinding {
        void onBinding(int position);
    }

    public void setOnItemDataBinding(OnItemDataBinding onItemDataBinding) {
        this.onItemDataBinding = onItemDataBinding;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(
        OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}