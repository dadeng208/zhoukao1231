package com.bawei.zhangyaojun1231.contract;

/**
 * @author 张耀军
 * @createTime 2019/12/31 9:02
 * @description
 */
public interface IContract {

    interface IModel {
        void getData(String url, ModelCallback callback);
    }

    interface ModelCallback {
        void modelSuccess(String json);

        void modelError(String error);
    }

    interface IView {
        void viewGetData(String json);
    }

    interface IPresenter {
        void toRequest(String url);
    }
}
