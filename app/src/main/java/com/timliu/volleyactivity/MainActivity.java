package com.timliu.volleyactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

    private ImageView imageView;
    private TextView textview;
    private String requestTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        textview = (TextView) findViewById(R.id.textview);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!"".equals(requestTag))
            MyApplication.getHttpQueues().cancelAll(requestTag);
    }


    // 图片获取
    public void btn_image_clicked(View view)
    {
        Log.i("TAG", "get_clicked");
        startActivity(new Intent(this, ImagesActivity.class));
    }

    // "二次封装"
    public void btn_2_clicked(View view)
    {
        Log.i("TAG", "get_clicked");
        startActivity(new Intent(this, SecActivity.class));
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
        textview.setText("get 开始了");
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        textview.setText(response);
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                        textview.setText(error.toString());
                    }


                }
        );
        requestTag = "volley_get";
        request.setTag(requestTag);
        MyApplication.getHttpQueues().add(request);
    }


    private void volley_getJson()
    {
        textview.setText("get Json开始了");
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        textview.setText(response.toString());
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                        textview.setText(error.toString());
                    }


                }
        );

        requestTag = "volley_getjson";
        request.setTag(requestTag);
        MyApplication.getHttpQueues().add(request);
    }

    private void volley_post()
    {
        textview.setText("post String 开始了");
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
        String url = "http://ip.taobao.com/service/getIpInfo.php?";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        textview.setText(response);
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                        textview.setText(error.toString());
                    }


                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("ip", "202.96.128.166");
                return hashMap;
            }
        };

        requestTag = "volley_post";
        request.setTag(requestTag);
        MyApplication.getHttpQueues().add(request);
    }


    private void volley_postJson()
    {
        textview.setText("post json 开始了");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("phone", "13429667914");
        hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
        JSONObject jsonParams = new JSONObject(hashMap);
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
        String url = "http://apis.juhe.cn/mobile/get?";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,jsonParams,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        textview.setText(response.toString());
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                        textview.setText(error.toString());
                    }


                }
        ){

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }


        };

        requestTag = "volley_postjson";
        request.setTag(requestTag);
        MyApplication.getHttpQueues().add(request);
    }

}
