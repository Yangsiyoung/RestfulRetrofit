package android.dolbom.ark.restfulretrofit;

import android.dolbom.ark.restfulretrofit.service.SeoulTechAPIService;
import android.dolbom.ark.restfulretrofit.service.data.CallManager;
import android.dolbom.ark.restfulretrofit.service.data.MyYoutube;
import android.dolbom.ark.restfulretrofit.service.data.SeoulTechLoginData;
import android.dolbom.ark.restfulretrofit.service.data.Student;
import android.dolbom.ark.restfulretrofit.service.data.User;
import android.dolbom.ark.restfulretrofit.service.data.YoutubeData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SeoulTechLoginData{

    public static final String URL  = "http://bad.watch";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        SeoulTechAPIService seoulTechAPIService = retrofit.create(SeoulTechAPIService.class);*/

        /*
        Student student = new Student();
        student.setUserId("12109350");
        student.setUserPassword("myssun1002!"); */

        //Call<ResponseBody> call = seoulTechAPIService.postUserDataSecond("12109350", "myssun1002!", "ko");

        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        SeoulTechAPIService seoulTechAPIService = retrofit.create(SeoulTechAPIService.class);

        Call<User> call = seoulTechAPIService.getUserData("quick", "리다양-3688");*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();

        SeoulTechAPIService seoulTechAPIService = retrofit.create(SeoulTechAPIService.class);

        Call<YoutubeData> call = seoulTechAPIService.getYoutubeData("1", "order");


        /*call.enqueue(new CallManager<YoutubeData>() {
            @Override
            public void onResponse(Call<YoutubeData> call, Response<YoutubeData> response) {

                try{

                    Log.d("myData", "RequstUrl is " + call.request().url());
                    ArrayList<MyYoutube> arrayListOfMyYoutube = new ArrayList<MyYoutube>();


                    arrayListOfMyYoutube = response.body().getArrayListOfMyYoutube();
                    for(MyYoutube myYoutube : arrayListOfMyYoutube){
                        Log.d("myData", myYoutube.getTitle());
                    }

                }catch (Exception e){
                    Log.d("error", "Retrofit Response Error is " + e.toString());
                }

            }

            @Override
            public void onFailure(Call<YoutubeData> call, Throwable t) {

            }

        });*/

        CallManager<YoutubeData> callManager = new CallManager<YoutubeData>(this);
        call.enqueue(callManager);


    }

    public void getData(ArrayList<MyYoutube> arrayListOfYoutubeData){
        Log.d("aaaa", "첫번째 유투브 제목은 " + arrayListOfYoutubeData.get(0).getTitle());
        editText.setText(arrayListOfYoutubeData.get(0).getTitle());

    }
}
