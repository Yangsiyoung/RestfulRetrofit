package android.dolbom.ark.restfulretrofit.service.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samsung on 2017-06-10.
 */

public class UserData {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("battletag")
    private String battleTag;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setBattleTag(String battleTag){
        this.battleTag = battleTag;
    }

    public String getBattleTag(){
        return this.battleTag;
    }
}
