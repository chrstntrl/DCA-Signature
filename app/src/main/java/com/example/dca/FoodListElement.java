package com.example.dca;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class FoodListElement {
    public Drawable pic1;


    public void setPic(Drawable pic) {
        this.pic1 = pic;
    }

    public FoodListElement(Drawable pic) {
        this.pic1 = pic;
    }

    public String name;
    public String desc;
    public String price;

    public FoodListElement(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Drawable getPic() {
        return pic1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}