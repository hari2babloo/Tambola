package com.a3x3conect.tambola;

/**
 * Created by b on 26/10/17.
 */

public class Data
{


    public String name;
    public int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Data(int image,String name) {
        this.name = name;
        this.image = image;

    }
}
