package com.smith.temp.dagger;

public class Food {
    private String meat = "Beef";
    private String drink = "Water";

    public Food() {
        this.drink = "水";
    }

    public String getMeat() {
        return meat;
    }

    public String getDrink() {
        return drink;
    }
}
