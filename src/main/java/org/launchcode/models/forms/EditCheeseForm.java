package org.launchcode.models.forms;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditCheeseForm {
    @NotNull @Size(min = 3, max = 15)
    private String name;

    @NotNull @Size(min = 1, message = "Description must not be empty.")
    private String description;

    @NotNull @Min(1) @Max(5)
    private int rating;

    @NotNull
    private int categoryId;

    @NotNull
    private int cheeseId;

    public void EditCheeseForm() {}

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
