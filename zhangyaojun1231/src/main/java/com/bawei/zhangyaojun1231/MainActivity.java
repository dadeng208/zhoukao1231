package com.bawei.zhangyaojun1231;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bawei.zhangyaojun1231.adapter.MyAdapter;
import com.bawei.zhangyaojun1231.base.BaseActivity;
import com.bawei.zhangyaojun1231.base.BasePresenter;
import com.bawei.zhangyaojun1231.entity.Bean;
import com.bawei.zhangyaojun1231.presenter.MyPresenter;
import com.bawei.zhangyaojun1231.util.OkhttpUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;


    @Override
    protected void initData() {
        if (OkhttpUtil.getInstance().getNetState(this)) {
            p.toRequest("http://blog.zhaoliang5156.cn/api/news/ranking.json");
        } else {
            Toast.makeText(this, "没网了呢，去检查你的网络吧", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected BasePresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void viewGetData(String json) {
        Bean bean = new Gson().fromJson(json, Bean.class);
        MyAdapter myAdapter = new MyAdapter(this, bean.getRanking());
        recycler.setAdapter(myAdapter);
        myAdapter.setOnItemClick(new MyAdapter.OnItemClick() {
            @Override
            public void onClick(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.textView)
    public void start(View view) {
        startActivity(new Intent(this, ShowActivity.class));
    }
}
