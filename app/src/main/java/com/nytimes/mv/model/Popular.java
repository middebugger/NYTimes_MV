package com.nytimes.mv.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Popular implements Serializable {

    @NonNull
    private final String url;

    @NonNull
    @SerializedName("adx_keywords")
    private final String adxKeywords;

    @NonNull
    private String column;

    @NonNull
    private String section;

    @NonNull
    private String byline;

    @NonNull
    private String type;

    @NonNull
    private String title;

    @NonNull
    @SerializedName("abstract")
    private String abstract_content;

    @NonNull
    @SerializedName("published_date")
    private String publishedDate;

    @NonNull
    private String source;

    @NonNull
    private String id;

    @NonNull
    @SerializedName("asset_id")
    private String assetId;

    @NonNull
    private int views;

    @NonNull
    private List<Media> media;

    public Popular(@NonNull String url, @NonNull String adxKeywords, @NonNull String column, @NonNull String section, @NonNull String byline, @NonNull String type, @NonNull String title, @NonNull String abstract_content, @NonNull String publishedDate, @NonNull String source, @NonNull String id, @NonNull String assetId, @NonNull int views, @NonNull List<Media> media) {
        this.url = url;
        this.adxKeywords = adxKeywords;
        this.column = column;
        this.section = section;
        this.byline = byline;
        this.type = type;
        this.title = title;
        this.abstract_content = abstract_content;
        this.publishedDate = publishedDate;
        this.source = source;
        this.id = id;
        this.assetId = assetId;
        this.views = views;
        this.media = media;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getAdxKeywords() {
        return adxKeywords;
    }

    @NonNull
    public String getColumn() {
        return column;
    }

    @NonNull
    public String getSection() {
        return section;
    }

    @NonNull
    public String getByline() {
        return byline;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getAbstract() {
        return abstract_content;
    }

    @NonNull
    public String getPublishedDate() {
        return publishedDate;
    }

    @NonNull
    public String getSource() {
        return source;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getAssetId() {
        return assetId;
    }

    @NonNull
    public List<Media> getMedia() {
        return media;
    }

    @Override
    public String toString() {
        return "MostPopular{" +
                "url='" + url + '\'' +
                ", adxKeywords='" + adxKeywords + '\'' +
                ", column='" + column + '\'' +
                ", section='" + section + '\'' +
                ", byline='" + byline + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", abs='" + abstract_content + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", source='" + source + '\'' +
                ", id='" + id + '\'' +
                ", assetId='" + assetId + '\'' +
                ", views=" + views +
                ", media=" + media +
                '}';
    }
}