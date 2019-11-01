package com.bawei.zxxmsz.bace;
/*
 *@auther:董青勇
 *@Date: 2019/10/29
 *@Time:14:09
 *@Description:${DESCRIPTION}
 **/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public  abstract class BaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        initView();
        initData();
    }

    protected abstract int provideLayoutId();

    protected abstract void initView();

    protected abstract void initData();
}
