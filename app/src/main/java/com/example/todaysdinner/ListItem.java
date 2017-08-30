package com.example.todaysdinner;

/**
 * Created by redrose on 11/17/16.
 */
public class ListItem {

    private   String image;
    private  String title;
    private String url;

    public ListItem() {
        super();
    }

    public  String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public   String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl(){ return url;}

    public void setUrl(){this.url = url;}

}
