package dise.vn.models;


import com.google.gson.annotations.SerializedName;

public class HashTag {

   @SerializedName("text")
    private String hashTag;
    public String getHashTag() {
        return hashTag;
    }
    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
}
