package android.dolbom.ark.restfulretrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.dolbom.ark.restfulretrofit.service.DolBomServerInterceptor;
import android.dolbom.ark.restfulretrofit.service.SeoulTechAPIService;
import android.dolbom.ark.restfulretrofit.service.data.CallManager;
import android.dolbom.ark.restfulretrofit.service.data.MyYoutube;
import android.dolbom.ark.restfulretrofit.service.data.SeoulTechLoginData;
import android.dolbom.ark.restfulretrofit.service.data.Student;
import android.dolbom.ark.restfulretrofit.service.data.User;
import android.dolbom.ark.restfulretrofit.service.data.YoutubeData;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SeoulTechLoginData{

    public static final String URL  = "http://bad.watch";
    public static final String URL_DOLBOM = "http://api.dorbom.com/v1/";


    private Button btnSelectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = getSharedPreferences("loginToken", MODE_PRIVATE);
        if(sharedPreferences.contains("token")){

        }else{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", getResources().getString(R.string.appToken));
            editor.commit();
        }

        Context context = getApplicationContext();


        btnSelectImage = (Button) findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(onClickListener);

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
        //editText.setText(arrayListOfYoutubeData.get(0).getTitle());

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnSelectImage:
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    startActivityForResult(intent, 0);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){

            try{

                Bitmap profileImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());


                File file = new File(getRealPath(data.getData()));
                //File file = new File((data.getData()).getPath());
                Log.d("aaaa", "Image URI is " + file.getPath());
                //RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                //RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(data.getData())), file);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("profile_image", file.getName(), requestBody);

                DolBomServerInterceptor dolBomServerInterceptor = new DolBomServerInterceptor(getApplicationContext());
                OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(dolBomServerInterceptor).build();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_DOLBOM).client(client).addConverterFactory(GsonConverterFactory.create()).build();
                SeoulTechAPIService seoulTechAPIService = retrofit.create(SeoulTechAPIService.class);
                Call<ResponseBody> call = seoulTechAPIService.postParentProfileImage(body);

               /* File file = new File(getRealPath(data.getData()));
                Log.d("aaaa", "Image URI is " + file.getPath());
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

                MultipartBody.Part body = MultipartBody.Part.createFormData("profile", file.getName(), requestBody);
                //RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(data.getData())), file);

                DolBomServerInterceptor dolBomServerInterceptor = new DolBomServerInterceptor(getApplicationContext());
                OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(dolBomServerInterceptor).build();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_DOLBOM).client(client).addConverterFactory(GsonConverterFactory.create()).build();
                SeoulTechAPIService seoulTechAPIService = retrofit.create(SeoulTechAPIService.class);
                Call<ResponseBody> call = seoulTechAPIService.postParentProfileImage(body);*/

                Log.d("aaaa", "여기까지는 오냐아...");

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            if (response.isSuccessful()) {
                                Log.d("aaaa", "Response is " + response.body().string());
                            }
                        }catch (Exception e){
                            Log.d("error", "Response Error is " + e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error", "실패..." + t.toString());
                    }
                });
            }catch (Exception e){
                Log.d("error", "Get Profile Error is " + e.toString());
            }
        }
    }

    public String getRealPath(Uri uri){
        String[] path = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, path, null, null, null);
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        //String realPath = cursor.getString(index);
        //realPath = realPath.substring(5);
        return cursor.getString(index);
    }
}
