package com.example.user.easynote;

/**
 * Created by User on 4/4/2561.
 */

public class Data {
    private String mTitle;
    private String mDescription;
    private int mIcon;

    public Data (String title, String description, int icon){
        this.mTitle = title;
        this.mDescription = description;
        this.mIcon = icon;
    }

    public String getmTitle() { return mTitle; }

    public String getmDescription() { return mDescription; }

    public int getmIcon() { return mIcon; }

    public void setmTitle(String title) { this.mTitle = title; }

    public void setmDescription(String description) { this.mDescription = description; }

    public void setmIcon(int icon) { this.mIcon = icon; }
}
