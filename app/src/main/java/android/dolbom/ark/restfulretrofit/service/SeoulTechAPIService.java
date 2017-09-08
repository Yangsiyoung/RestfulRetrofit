package android.dolbom.ark.restfulretrofit.service;

import android.dolbom.ark.restfulretrofit.service.data.Student;
import android.dolbom.ark.restfulretrofit.service.data.User;
import android.dolbom.ark.restfulretrofit.service.data.YoutubeData;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by samsung on 2017-06-10.
 */

public interface SeoulTechAPIService {

    @POST("/user/seouolTechLogin.face")
    Call<ResponseBody> postUserData (@Body Student student);

    @FormUrlEncoded
    @POST("/user/seouolTechLogin.face")
    Call<ResponseBody> postUserDataSecond (@Field("userId") String userId, @Field("password") String userPassword, @Field("ssoLangKnd") String ssoLangKnd);

    @GET("/api/user/{gameType}/{userName}")
    Call<User> getUserData(@Path("gameType") String gameType, @Path("userName") String userName);

    @GET("/api/youtube-list")
    Call<YoutubeData> getYoutubeData(@Query("page") String page, @Query("order") String order);

    /*@Multipart
    @POST("parents/profile_image")
    Call<ResponseBody> postParentProfileImage(@Part("profile_image")  RequestBody profileImage);*/


    @Multipart
    @POST("parents/profile_image")
    Call<ResponseBody> postParentProfileImage(@Part MultipartBody.Part profileImage);

}
