package dise.vn.models;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by carot on 4/4/2016.
 */
public class Media {

    @SerializedName("media_url_https")
    private String urlPhoto;
    @SerializedName("sizes")
    private ImageSize imageSizes;
    @SerializedName("url")
    private String urlInBody;

    public String getUrlInBody() {
        return urlInBody;
    }

    public void setUrlInBody(String urlInBody) {
        this.urlInBody = urlInBody;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public ImageSize getImageSizes() {
        return imageSizes;
    }

    public void setImageSizes(ImageSize imageSizes) {
        this.imageSizes = imageSizes;
    }


}

