package com.bawei.zxxmsz.bace;
/*
 *@auther:董青勇
 *@Date: 2019/10/29
 *@Time:14:10
 *@Description:${DESCRIPTION}
 **/

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(provideLayoutId(), container, false);
          initView(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract int provideLayoutId();

    protected abstract void initView(View inflate);

    protected abstract void initData();
}
