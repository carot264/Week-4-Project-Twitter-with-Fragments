package dise.vn.models;


import com.google.gson.annotations.SerializedName;

public class Dimension {
    @SerializedName("w")
    private int width;
    @SerializedName("h")
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
