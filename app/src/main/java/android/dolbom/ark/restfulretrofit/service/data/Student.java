package android.dolbom.ark.restfulretrofit.service.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samsung on 2017-06-10.
 */

public class Student {

    @SerializedName("userId")
    private String userId;

    @SerializedName("password")
    private String userPassword;

    @SerializedName("ssoLangKnd")
    private String ssoLangKnd = "ko";

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserPassword(String userPassword){
        this.userPassword= userPassword;
    }

    public String getUserPassword(){
        return this.userPassword;
    }

    public void setSsoLangKnd(String ssoLangKnd){
        this.ssoLangKnd = ssoLangKnd;
    }

    public String getSsoLangKnd(){
        return this.ssoLangKnd;
    }

}
