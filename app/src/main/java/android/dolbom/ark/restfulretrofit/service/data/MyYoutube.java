package android.dolbom.ark.restfulretrofit.service.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samsung on 2017-06-10.
 */

public class MyYoutube {

    @SerializedName("youtube_id")
    private String youtubeId;

    @SerializedName("title")
    private String title;

    public void setYoutubeId(String youtubeId){
        this.youtubeId = youtubeId;
    }

    public String getYoutubeId(){
        return this.youtubeId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}
