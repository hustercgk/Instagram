package com.example.huster.instagram.util;

import android.content.Context;
import android.util.Log;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * Created by huster on 2016/9/18.
 */
public class PkuHttp {
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private static PkuHttp pkuHttp = null;
    public static  PkuHttp getInstance(){
        if(pkuHttp==null){
            pkuHttp = new PkuHttp();
        }
        return pkuHttp;
    }

    public String doGet(Context context,  String url) throws IOException{
        Request request = new Request.Builder().url(url).build();
        Response response = mOkHttpClient.newCall(request).execute();
        Log.d("kangkang", "doGet: "+response);
        if(response.isSuccessful()){
            Log.d("kangkang", "isSuccessful: ");
        }
        return null;
    }
}
