package com.timliu.volleyactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import utils.VolleyInterface;
import utils.VolleyRequest;

/**
 * Created by tim2 on 15/8/9.
 */
public class SecActivity extends Activity
{
    private ImageView imageView;
    private TextView textview;
    private String requestTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        imageView = (ImageView) findViewById(R.id.imageView);
        textview = (TextView) findViewById(R.id.textview);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!"".equals(requestTag))
            MyApplication.getHttpQueues().cancelAll(requestTag);
    }



    // GET 获取String类型请求
    public void get_clicked(View view)
    {
        Log.i("TAG", "get_clicked");
        volley_get();
    }

    // GET 获取Json类型请求
    public void getjson_clicked(View view)
    {
        Log.i("TAG", "getjson_clicked");
        volley_getJson();
    }


    // POST 获取String类型请求
    public void post_clicked(View view)
    {
        Log.i("TAG", "post_clicked");
        volley_post();
    }

    // POST 获取Json类型请求
    public void postjson_clicked(View view)
    {
        Log.i("TAG", "postjson_clicked");
        volley_postJson();
    }


    private void volley_get()
    {
        textview.setText("封装get 开始了");
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";
        requestTag = "volley_post";
        VolleyRequest.RequestGet(this, url, requestTag, new VolleyInterface(this, VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySuccess(String result) {
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textview.setText("GETresult=" + json.toString());
            }

            @Override
            public void onMyError(VolleyError error) {
                textview.setText(error.toString());
            }
        });
    }

    private void volley_getJson()
    {
        Toast.makeText(this, "请自己实现get json的二次封装！",Toast.LENGTH_SHORT).show();
        textview.setText("请自己实现get json的二次封装！");
    }

    private void volley_post()
    {
        textview.setText("封装post String 开始了");
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
        String url = "http://ip.taobao.com/service/getIpInfo.php?";

        requestTag = "volley_get";
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("ip", "202.96.128.166");
        VolleyRequest.RequestPost(this, url, requestTag, hashMap, new VolleyInterface(this, VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySuccess(String result) {
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textview.setText("POSTresult=" + json.toString());
            }

            @Override
            public void onMyError(VolleyError error) {
                textview.setText(error.toString());
            }
        });
    }


    private void volley_postJson() {

        Toast.makeText(this, "自己实现post json的二次封装！",Toast.LENGTH_SHORT).show();
        textview.setText("自己实现post json的二次封装！");

    }

}
