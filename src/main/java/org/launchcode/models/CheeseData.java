package org.launchcode.models;

import java.util.ArrayList;

public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    public static ArrayList<Cheese> getAll() {
        return cheeses;
    }

    public static void add(Cheese newCheese) {
        cheeses.add(newCheese);
    }

    public static void remove(int id) {
        cheeses.remove(getById(id));
    }

    public static Cheese getById(int id) {
        for (Cheese cheese : cheeses) {
            if (cheese.getCheeseId() == id) return cheese;
        }
        return null;
    }
}
