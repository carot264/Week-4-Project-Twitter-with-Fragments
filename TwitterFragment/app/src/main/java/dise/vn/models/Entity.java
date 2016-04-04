package dise.vn.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Entity {

    @SerializedName("media")
    private ArrayList<Media> media;
    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }
    @SerializedName("hashtags")
    private ArrayList<HashTag> hashTag;
    public ArrayList<HashTag> getHashTag() {
        return hashTag;
    }

    public void setHashTag(ArrayList<HashTag> hashTag) {
        this.hashTag = hashTag;
    }









}
