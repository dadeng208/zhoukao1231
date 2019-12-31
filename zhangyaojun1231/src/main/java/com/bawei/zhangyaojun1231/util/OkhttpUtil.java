package com.bawei.zhangyaojun1231.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 张耀军
 * @createTime 2019/12/31 8:52
 * @description
 */
public class OkhttpUtil {

    private Handler handler = new Handler();
    private final OkHttpClient client;

    //单例——私有化构造方法
    private OkhttpUtil() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static class SingleHolder {
        private static final OkhttpUtil UTIL = new OkhttpUtil();
    }

    public static OkhttpUtil getInstance() {
        return SingleHolder.UTIL;
    }

    //封装判断网络状态方法
    public boolean getNetState(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) return info.isConnected();
        return false;
    }

    //封装OK的Get请求
    public void doGet(String url, final OkCallback callback) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.okError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callback.okSuccess(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public interface OkCallback {
        void okSuccess(String json);

        void okError(String error);
    }
}
