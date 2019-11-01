package com.bawei.zxxmsz;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.bawei.zxxmsz.adapter.Myadapter;
import com.bawei.zxxmsz.bace.BaceActivity;
import com.bawei.zxxmsz.bean.JsonBean;
import com.bawei.zxxmsz.net.NetState;
import com.bawei.zxxmsz.net.NetUtils;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends BaceActivity {
     private GridView gv;

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
   gv=findViewById(R.id.gv);
    }

    @Override
    protected void initData() {

        getData();

    }

    private void getData() {
          String url="http://blog.zhaoliang5156.cn/api/shop/fulishe1.json";
          if(NetState.getInstance().hasNet(this)){
              NetUtils.getInstance().doGet(url, new NetUtils.MyCallBack() {
                  @Override
                  public void onDoGetSuccess(String json) {
                      JsonBean jsonBean = new Gson().fromJson(json, JsonBean.class);
                      List<JsonBean.DataBean> data = jsonBean.getData();

                  gv.setAdapter(new Myadapter(MainActivity.this,data));
                  }

                  @Override
                  public void onDoGetPhotoSuccess(Bitmap bitmap) {

                  }
              });


          }else {
              Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
          }

    }
}
