package com.wesksky.magicrecyclerview;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Magic {

    private static Magic instance;

    private List<Class<? extends IBaseData>> baseDataList = new ArrayList<>();

    /**
     * @param magicInit 初始化对象
     */
    public static void init(IMagicInit magicInit) {
        getInstance().setDataTypeList(magicInit.initDataTypeList());
    }

    private void setDataTypeList(List<Class<? extends IBaseData>> list) {
        baseDataList = list;
        for (Class clazz : baseDataList) {
            Log.i("MagicRecyclerView",
                "注册类:" + clazz.getName());
        }
    }

    public int getBaseDataTypeByClass(Class clazz) {
        int type = MagicAdapter.UNKNOWN_TYPE;
        if (baseDataList == null) {
            return type;
        }
        if (clazz == null) {
            return type;
        }
        for (Class baseDataClass : baseDataList) {
            if (baseDataClass == clazz) {
                return baseDataList.indexOf(baseDataClass);
            }
        }
        return type;
    }

    public Class getBaseDataByPosition(int position) {
        if (baseDataList == null) {
            return null;
        } else {
            return baseDataList.get(position);
        }
    }

    private Magic() {

    }

    public static Magic getInstance() {
        if (instance == null) {
            synchronized (Magic.class) {
                if (instance == null) {
                    instance = new Magic();
                }
            }
        }
        return instance;
    }

}
