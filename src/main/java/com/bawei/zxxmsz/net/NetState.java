package com.bawei.zxxmsz.net;
/*
 *@auther:董青勇
 *@Date: 2019/10/29
 *@Time:13:55
 *@Description:${DESCRIPTION}
 **/

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetState {
    static  NetState netState=new NetState();

    public static NetState getInstance() {
        return netState;
    }

    private NetState() {
    }
  public  boolean hasNet(Context context){
      ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
      if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()) {
          return true;
      }else {
          return  false;
      }

  }
    public  boolean isWifi(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return  false;
        }

    }
    public  boolean isMobile(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return  false;
        }

    }


}
