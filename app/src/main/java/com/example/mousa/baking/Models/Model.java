package com.example.mousa.baking.Models;
import org.json.JSONArray;

public class Model  {
    private String name;
  private  JSONArray jsonElements=new JSONArray();
  private   JSONArray jsonArray=new JSONArray();
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    private String img;


    //---------------------Constructor For MainActivity-----------------------------

    public Model(String name, JSONArray jsonElements, JSONArray jsonArray,String img) {
        this.name = name;
        this.jsonElements = jsonElements;
        this.jsonArray = jsonArray;
        this.img=img;
    }
    //----------------Constructor for Fragment---------------------

    public Model(String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

//----------------Methods Get And Sets----------------------------


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public JSONArray getJsonElements() {
        return jsonElements;
    }

    public void setJsonElements(JSONArray jsonElements) {
        this.jsonElements = jsonElements;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
