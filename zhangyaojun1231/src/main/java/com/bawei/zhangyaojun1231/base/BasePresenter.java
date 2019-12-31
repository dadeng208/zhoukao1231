package com.bawei.zhangyaojun1231.base;

import com.bawei.zhangyaojun1231.contract.IContract;

import java.lang.ref.WeakReference;

/**
 * @author 张耀军
 * @createTime 2019/12/31 9:06
 * @description
 */
public abstract class BasePresenter<V extends BaseActivity> implements IContract.IPresenter {

    protected WeakReference<V> v;

    protected void attachView(V v) {
        this.v = new WeakReference<>(v);
    }

    protected void detachView() {
        v.clear();
        v = null;
    }

    protected V getView() {
        return v.get();
    }
}
