package dise.vn.models;


import com.google.gson.annotations.SerializedName;

public class ImageSize {

    @SerializedName("small")
    private Dimension Small;
    @SerializedName("thumb")
    private Dimension Thumb;
    @SerializedName("large")
    private Dimension Large;
    @SerializedName("medium")
    private Dimension Medium;

    public Dimension getSmall() {
        return Small;
    }

    public void setSmall(Dimension small) {
        Small = small;
    }

    public Dimension getThumb() {
        return Thumb;
    }

    public void setThumb(Dimension thumb) {
        Thumb = thumb;
    }

    public Dimension getLarge() {
        return Large;
    }

    public void setLarge(Dimension large) {
        Large = large;
    }

    public Dimension getMedium() {
        return Medium;
    }

    public void setMedium(Dimension medium) {
        Medium = medium;
    }

}
