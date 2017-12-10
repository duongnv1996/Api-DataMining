package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DuongKK on 12/10/2017.
 */
public class Phone {
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("urlDetail")
    String urlDetail;
    @SerializedName("urlImage")
    String urlImage;
    @SerializedName("content")
    String content;
    @SerializedName("pricing")
    long pricing;
    public String getId() {
        return id;
    }

    public long getPricing() {
        return pricing;
    }

    public void setPricing(long pricing) {
        this.pricing = pricing;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(String urlDetail) {
        this.urlDetail = urlDetail;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
