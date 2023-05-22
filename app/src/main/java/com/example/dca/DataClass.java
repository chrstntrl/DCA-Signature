package com.example.dca;

public class DataClass {
    private String prodName;
    private String prodDesc;
    private String prodPrice;
    private String prodImage;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getprodName() {
        return prodName;
    }
    public String getprodDesc() {
        return prodDesc;
    }
    public String getprodPrice() {
        return prodPrice;
    }
    public String getprodImage() {
        return prodImage;
    }
    public DataClass(String prodName, String prodDesc, String prodPrice, String prodImage) {
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodImage = prodImage;
    }
    public DataClass(){
    }
}