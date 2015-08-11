package utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.timliu.volleyactivity.MyApplication;

import java.util.Map;

/**
 * Created by tim2 on 15/8/9.
 */
public class VolleyRequest
{
    public static StringRequest stringRequest;
    public static Context context;

    public static void RequestGet(Context context,String url, String tag, VolleyInterface vif)
    {

        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,url,vif.loadingListener(),vif.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        // 不写也能执行
//        MyApplication.getHttpQueues().start();
    }

    public static void RequestPost(Context context,String url, String tag,final Map<String, String> params, VolleyInterface vif)
    {
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST,url,vif.loadingListener(),vif.errorListener())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        // 不写也能执行
//        MyApplication.getHttpQueues().start();
    }
}
