package com.org.coffeemachine.entity;

import java.util.HashMap;
import java.util.Map;

public class Beverage {
    private final String name;
    private final Map<Ingredient, Integer> ingredientQuantity;

    private Beverage(String name, Map<Ingredient, Integer> ingredientQuantity) {
        this.name = name;
        this.ingredientQuantity = ingredientQuantity;
    }

    public static Beverage getInstance(String name) {
        return new Beverage(name, new HashMap<>());
    }

    public Map<Ingredient, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getName() {
        return name;
    }
}
