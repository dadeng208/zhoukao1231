package com.bawei.zhangyaojun1231.presenter;

import com.bawei.zhangyaojun1231.base.BasePresenter;
import com.bawei.zhangyaojun1231.contract.IContract;
import com.bawei.zhangyaojun1231.model.MyModel;

/**
 * @author 张耀军
 * @createTime 2019/12/31 9:11
 * @description
 */
public class MyPresenter extends BasePresenter {
    @Override
    public void toRequest(String url) {
        new MyModel().getData(url, new IContract.ModelCallback() {
            @Override
            public void modelSuccess(String json) {
                getView().viewGetData(json);
            }

            @Override
            public void modelError(String error) {

            }
        });
    }
}
