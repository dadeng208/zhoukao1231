package com.bawei.zhangyaojun1231.model;


import com.bawei.zhangyaojun1231.contract.IContract;
import com.bawei.zhangyaojun1231.util.OkhttpUtil;

/**
 * @author 张耀军
 * @createTime 2019/12/31 9:04
 * @description
 */
public class MyModel implements IContract.IModel {
    @Override
    public void getData(String url, final IContract.ModelCallback callback) {
        OkhttpUtil.getInstance().doGet(url, new OkhttpUtil.OkCallback() {
            @Override
            public void okSuccess(String json) {
                callback.modelSuccess(json);
            }

            @Override
            public void okError(String error) {
                callback.modelError(error);
            }
        });
    }
}
