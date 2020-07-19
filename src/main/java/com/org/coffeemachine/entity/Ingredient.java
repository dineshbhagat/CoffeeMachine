package com.org.coffeemachine.entity;

import java.util.Objects;

public class Ingredient {
    private final String name;

    private Ingredient(String name) {
        this.name = name;
    }

    public static Ingredient getInstance(String name) {
        return new Ingredient(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
