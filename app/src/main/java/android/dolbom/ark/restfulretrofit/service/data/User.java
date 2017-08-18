package android.dolbom.ark.restfulretrofit.service.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samsung on 2017-06-10.
 */

public class User {

    @SerializedName("responseCode")
    private String responseCode;

    @SerializedName("userData")
    private UserData userData;

    public void setResponseCode(String responseCode){
        this.responseCode = responseCode;
    }

    public String getResponseCode(){
        return this.responseCode;
    }

    public void setUserData(UserData userData){
        this.userData = userData;
    }

    public UserData getUserData(){
        return this.userData;
    }
}
