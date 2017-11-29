package com.wesksky.magicrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class Magic {

    private static Magic instance;

    private List<? extends BaseData> baseDataList = new ArrayList<>();

    /**
     * @param magicInit 初始化对象
     */
    public static void init(IMagicInit magicInit) {
        getInstance().setDataTypeList(magicInit.initDataTypeList());
    }

    private void setDataTypeList(List<? extends BaseData> list) {
        baseDataList = list;
    }

    public int getBaseDataTypeByClass(Class clazz) {
        int type = MagicAdapter.UNKNOWN_TYPE;
        if (baseDataList == null) {
            return type;
        }
        for (BaseData baseData : baseDataList) {
            if (baseData.getClass() == clazz) {
                return baseDataList.indexOf(baseData);
            }
        }
        return type;
    }

    public Class getBaseDataByPosition(int position) {
        if (baseDataList == null) {
            return null;
        } else {
            return baseDataList.get(position).getClass();
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
