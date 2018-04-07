package org.launchcode.models;

import java.util.ArrayList;

public class UserData {
    static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getAll() {
        return users;
    }

    public static void add(User newUser) {
        users.add(newUser);
    }

    public static void remove(int id) {
        users.remove(getById(id));
    }

    public static User getById(int id) {
        for (User user : users) {
            if (user.getUserId() == id) return user;
        }
        return null;
    }
}
