package com.example.dca;

public class DataClass {
    private String dataCategory;
    private String dataName;
    private String dataDesc;
    private String dataPrice;
    private String dataImage;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataCategory() {
        return dataCategory;
    }
    public String getDataName() {
        return dataName;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataPrice() {
        return dataPrice;
    }
    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataCategory, String dataName, String dataDesc, String dataPrice, String dataImage) {
        this.dataCategory = dataCategory;
        this.dataName = dataName;
        this.dataDesc = dataDesc;
        this.dataPrice = dataPrice;
        this.dataImage = dataImage;
    }
    public DataClass(){
    }
}