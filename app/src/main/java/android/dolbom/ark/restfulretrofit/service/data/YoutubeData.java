package android.dolbom.ark.restfulretrofit.service.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by samsung on 2017-06-10.
 */

public class YoutubeData {

    @SerializedName("responseCode")
    private String responseCode;

    @SerializedName("youtubeData")
    private ArrayList<MyYoutube> arrayListOfMyYoutube = new ArrayList<>();

    public void setResponseCode(String responseCode){
        this.responseCode = responseCode;
    }

    public String getResponseCode(){
        return this.responseCode;
    }

    public void setArrayListOfMyYoutube(ArrayList<MyYoutube> arrayListOfMyYoutube){
        this.arrayListOfMyYoutube = arrayListOfMyYoutube;
    }

    public ArrayList<MyYoutube> getArrayListOfMyYoutube(){
        return this.arrayListOfMyYoutube;
    }

}
