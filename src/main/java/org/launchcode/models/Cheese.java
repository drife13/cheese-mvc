package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity // store this class in a database
public class Cheese {

    @Id @GeneratedValue
    private int id;

    @NotNull @Size(min = 3, max = 15)
    private String name;

    @NotNull @Size(min = 1, message = "Description must not be empty.")
    private String description;

    @NotNull @Min(1) @Max(5)
    private int rating;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() { }

    public void edit(Cheese updatedCheese){
        this.name = updatedCheese.getName();
        this.description = updatedCheese.getDescription();
        this.category = updatedCheese.getCategory();
        this.rating = updatedCheese.getRating();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
