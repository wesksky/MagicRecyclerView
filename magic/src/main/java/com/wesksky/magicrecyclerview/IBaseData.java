package com.wesksky.magicrecyclerview;

import java.io.Serializable;

/**
 * @author sky
 * @date 2017/11/27
 */
public interface IBaseData extends Serializable {
    Class<? extends BaseHolder> bindHolder();
}
