package com.bawei.zxxmsz.net;
/*
 *@auther:董青勇
 *@Date: 2019/10/29
 *@Time:13:58
 *@Description:${DESCRIPTION}
 **/

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {
    static  NetUtils netUtils=new NetUtils();

    public static NetUtils getInstance() {
        return netUtils;
    }

    private NetUtils() {
    }
    public  interface  MyCallBack{
        void  onDoGetSuccess(String json);
        void  onDoGetPhotoSuccess(Bitmap bitmap);
    }

    public  void doGet(final String temUrl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                myCallBack.onDoGetSuccess(s);
            }

            @Override
            protected String doInBackground(String... strings) {
                String json="";
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                try {
                    URL url = new URL(temUrl);
                     httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    if(httpURLConnection.getResponseCode()==200){
                         inputStream = httpURLConnection.getInputStream();
                         json = ioString(inputStream);
                    }else {
                        Log.e("tag", "请求失败 " );
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }


                return json;
            }
        }.execute();


    }

    public String ioString(InputStream inputStream) {
        String s="";
        ByteArrayOutputStream byteArrayOutputStream=null;
        try {
            byte[] bytes = new byte[1024];
            int i=-1;
            byteArrayOutputStream = new ByteArrayOutputStream();
            while ((i=inputStream.read(bytes))!=-1){
                 byteArrayOutputStream.write(bytes,0,i);
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
             s = new String(bytes1);


        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         return s;
    }


    public  void doGetPhoto(final String temUrl, final MyCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBack.onDoGetPhotoSuccess(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap bitmap=null;
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                try {
                    URL url = new URL(temUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    if(httpURLConnection.getResponseCode()==200){
                        inputStream = httpURLConnection.getInputStream();
                        bitmap = ioBitmap(inputStream);
                    }else {
                        Log.e("tag", "请求失败 " );
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }


                return bitmap;
            }
        }.execute();
    }

    public  Bitmap ioBitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }

}
