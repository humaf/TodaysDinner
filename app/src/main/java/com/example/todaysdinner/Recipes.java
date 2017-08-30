package com.example.todaysdinner;

/**
 * Created by redrose on 8/18/16.
 */
public class Recipes {

    private String title;
    private String href;
    private String thumbnail;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }



    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Recipes(String title,String thumbnail,String href){

        super();
        this.title =title;
        this.thumbnail = thumbnail;
        this.href = href;
    }

}
