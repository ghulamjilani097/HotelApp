package com.memor.thinkers.jilani.hotelapp;

/**
 * Created by Jilani on 22-02-2018.
 */

public class Data {
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFooddetails() {
        return fooddetails;
    }

    public void setFooddetails(String fooddetails) {
        this.fooddetails = fooddetails;
    }

    String food,fooddetails;

    public Data(String food, String fooddetails) {
        this.food = food;
        this.fooddetails = fooddetails;
    }


}
