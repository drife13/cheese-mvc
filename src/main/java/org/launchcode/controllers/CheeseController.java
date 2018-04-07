package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.forms.EditCheeseForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    CheeseDao cheeseDao;

    @Autowired // Spring understands how to create concrete implementation of this interface
    CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese()); // key is name of model by default
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam int categoryId,
                                       @ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category category = categoryDao.findOne(categoryId);
        newCheese.setCategory(category);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {

        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "cheese/index";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        Cheese theCheese = cheeseDao.findOne(cheeseId);
        model.addAttribute("cheese", theCheese);
        model.addAttribute("title", "Edit Cheese " + theCheese.getName() + " (ID=" + cheeseId +")");
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid EditCheeseForm form, Errors errors) {

        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            model.addAttribute("title", "Edit Cheese " + theCheese.getName() + " (ID=" + form.getCheeseId() +")");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/edit";
        }

        Category category = categoryDao.findOne(form.getCategoryId());

        theCheese.setName(form.getName());
        theCheese.setDescription(form.getDescription());
        theCheese.setRating(form.getRating());
        theCheese.setCategory(category);

        cheeseDao.save(theCheese);

        return "redirect:";
    }

}
