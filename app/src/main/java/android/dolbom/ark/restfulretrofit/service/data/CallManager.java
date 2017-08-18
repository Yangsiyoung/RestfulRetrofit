package android.dolbom.ark.restfulretrofit.service.data;

import android.content.Context;
import android.dolbom.ark.restfulretrofit.MainActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by samsung on 2017-08-18.
 */

public class CallManager<T> implements Callback<T> {

    private Context context;

    @Nullable
    private ArrayList<? extends Object> arrayListOfData = new ArrayList<>();

    private MainActivity activity;

    public CallManager() {

    }

    public CallManager(Context context) {
        this.context = context;
    }

    public CallManager(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        /*if(response.body().getClass().isInstance(YoutubeData.class)){
            Log.d("aaaa", "이거슨 youtube data");
        }*/

        if (response.body() instanceof YoutubeData ? true : false) {
            Log.d("aaaa", "이거슨 youtube data");


            ArrayList<MyYoutube> arrayListOfMyYoutube = new ArrayList<MyYoutube>();


            arrayListOfMyYoutube = ((YoutubeData) response.body()).getArrayListOfMyYoutube();
            arrayListOfData = arrayListOfMyYoutube;
            for (MyYoutube myYoutube : arrayListOfMyYoutube) {
                Log.d("myData", myYoutube.getTitle());
            }

        }

        Log.d("aaaa", "현재 유투브 첫번째 제목은 " + ((ArrayList<MyYoutube>)arrayListOfData).get(0).getTitle());
        activity.getData((ArrayList<MyYoutube>)arrayListOfData);


    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    public ArrayList<MyYoutube> getData() {
        return (ArrayList<MyYoutube>) arrayListOfData;
    }
}
