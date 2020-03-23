package com.example.myapplication.po;

public class TabBean {
    private int image;
    private String title;
    private boolean textColor;

    public void setImage(int image) {
        this.image = image;
    }

    public void setTextColor(boolean textColor) {
        this.textColor = textColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public boolean getTextColor(){
        return textColor;
    }
    public String getTitle() {
        return title;
    }
}
