package com.example.pizzeri.models;

import java.io.Serializable;

public class Pizza implements Serializable {
    private String size;
    private String title;
    private int price;
    private String ingredients;

    public static final String SMALL = "Маленька";
    public static final String MEDIUM = "Середня";
    public static final String BIG = "Велика";
    public static final String VERY_BIG = "Дуууже велика";

    public static final int SMALL_PIZZA_COST = 95;
    public static final int MEDIUM_PIZZA_COST = 130;
    public static final int BIG_PIZZA_COST = 200;
    public static final int VERY_BIG_PIZZA_COST = 250;

    public static final String MARGHERITA = "Маргарита";
    public static final String SICILIAN = "Сицілійська";
    public static final String PEPPERONI = "Пепероні";
    public static final String BAVARIAN = "Баварська";

    public static final int MARGHERITA_PRICE = 15;
    public static final int SICILIAN_PRICE = 20;
    public static final int PEPPERONI_PRICE = 25;
    public static final int BAVARIAN_PRICE = 30;
    public static final int INGREDIENTS_PRICE = 5;

    public Pizza() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void calculatePrice() {
        price = 0;

        switch (size) {
            case SMALL:
                price += SMALL_PIZZA_COST;
                break;
            case MEDIUM:
                price += MEDIUM_PIZZA_COST;
                break;
            case BIG:
                price += BIG_PIZZA_COST;
                break;
            case VERY_BIG:
                price += VERY_BIG_PIZZA_COST;
                break;
        }

        switch (title) {
            case MARGHERITA:
                price += MARGHERITA_PRICE;
                break;
            case SICILIAN:
                price += SICILIAN_PRICE;
                break;
            case PEPPERONI:
                price += PEPPERONI_PRICE;
                break;
            case BAVARIAN:
                price += BAVARIAN_PRICE;
                break;
        }

        int ingredientsAmount = 0;

        if (ingredients.length() != 0) {
            ingredientsAmount = ingredients.split(", ").length;
        }

        price += Pizza.INGREDIENTS_PRICE * ingredientsAmount;
    }
}

