package com.timliu.volleyactivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import utils.BitmapCache;
import utils.VolleyInterface;
import utils.VolleyRequest;

/**
 * Created by tim2 on 15/8/9.
 */
public class ImagesActivity extends Activity
{
    private ImageView imageView;
    private TextView textview;

    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        imageView = (ImageView) findViewById(R.id.imageView);
        textview = (TextView) findViewById(R.id.textview);
        networkImageView = (NetworkImageView) findViewById(R.id.networkImageView);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }



    // 获取图片
    public void get_image_clicked(View view)
    {
        Log.i("TAG", "get_image_clicked");
        get_image();
    }

    // 获取图片缓存
    public void get_imageloader_clicked(View view)
    {
        Log.i("TAG", "get_imageloader_clicked");
        get_imageloader();
    }

    // 用NetworkImageView获取图片
    public void btn_get_network_image_clicked(View view)
    {
        Log.i("TAG", "btn_get_network_image_clicked");
        btn_get_network_image();
    }






    private void get_image()
    {
        textview.setText("获取图片开始了");
        imageView.setImageBitmap(null);
        networkImageView.setImageBitmap(null);
        // 错误url：String url = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166"
//        String url = "http://d.hiphotos.baidu.com/image/h%3D200/sign=31db160e2a738bd4db21b531918a876c/6a600c338744ebf8f1230decddf9d72a6159a797.jpg";
        String url = "http://h.hiphotos.baidu.com/image/pic/item/d53f8794a4c27d1e3584e91b1fd5ad6edcc4384b.jpg";

        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                textview.setText("获取图片成功");
                imageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textview.setText("获取图片错误");
                imageView.setImageBitmap(null);
            }
        });

        MyApplication.getHttpQueues().add(request);

    }

    private void get_imageloader()
    {
        textview.setText("获取图片开始了");
        networkImageView.setImageBitmap(null);
        imageView.setImageBitmap(null);
        String url = "http://g.hiphotos.baidu.com/image/pic/item/21a4462309f790521631d9e908f3d7ca7bcbd53f.jpg";

        ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(), new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.default_icon , R.drawable.error );

        loader.get(url, listener);

    }

    private void btn_get_network_image()
    {
        
        String url = "http://g.hiphotos.baidu.com/image/pic/item/0ff41bd5ad6eddc487907ddd3cdbb6fd526633a5.jpg";
        textview.setText("获取NetworkImageView图片开始了");
        imageView.setImageBitmap(null);
        ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(), new BitmapCache());
        networkImageView.setErrorImageResId(R.drawable.error2);
        networkImageView.setDefaultImageResId(R.drawable.default_icon);
        networkImageView.setImageUrl(url, loader);

    }

}
