package com.example.todaysdinner;

/**
 * Created by redrose on 10/7/17.
 */

public class RecyclerItem {

    private  static String ingredient ;

    public RecyclerItem(){
        super();
    }


    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public  static String getIngredient(){
        return ingredient;
    }
}
