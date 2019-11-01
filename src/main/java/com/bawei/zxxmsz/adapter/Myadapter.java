package com.bawei.zxxmsz.adapter;
/*
 *@auther:董青勇
 *@Date: 2019/11/1
 *@Time:19:26
 *@Description:${DESCRIPTION}
 **/

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zxxmsz.R;
import com.bawei.zxxmsz.bean.JsonBean;
import com.bawei.zxxmsz.net.NetUtils;

import java.util.List;

public class Myadapter extends BaseAdapter {
    private Context context;
    private List<JsonBean.DataBean>list;

    public Myadapter(Context context, List<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JsonBean.DataBean dataBean = list.get(position);
        ViewHolder holder;
        holder=new ViewHolder();
        if(convertView==null){
             convertView=View.inflate(context, R.layout.layout,null);
             holder.name=convertView.findViewById(R.id.name);
             holder.imgg=convertView.findViewById(R.id.imgg);
             convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(dataBean.getGoods_name());
        final ViewHolder finalHolder = holder;
        NetUtils.getInstance().doGetPhoto(dataBean.getGoods_thumb(), new NetUtils.MyCallBack() {
            @Override
            public void onDoGetSuccess(String json) {

            }

            @Override
            public void onDoGetPhotoSuccess(Bitmap bitmap) {
        finalHolder.imgg.setImageBitmap(bitmap);
            }
        });


        return convertView;
    }

    class  ViewHolder{
        ImageView imgg;
        TextView name;
    }
}
