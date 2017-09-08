package android.dolbom.ark.restfulretrofit.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by samsung on 2017-09-02.
 */

public class DolBomServerInterceptor implements Interceptor {

    private Context context;
    public DolBomServerInterceptor(Context context){
        this.context = context;
    };

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        SharedPreferences sharedPreferences = context.getSharedPreferences("loginToken",Context.MODE_PRIVATE);

        request = request.newBuilder().addHeader("token", sharedPreferences.getString("token", "")).build();

        Response response = chain.proceed(request);



        return response;
    }
}
