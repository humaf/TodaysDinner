package com.example.todaysdinner;

/**
 * Created by redrose on 10/7/17.
 */

public class RecyclerItem {

    private   String ingredient ;

    public RecyclerItem(){
        super();
    }


    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public   String getIngredient(){
        return ingredient;
    }
}
