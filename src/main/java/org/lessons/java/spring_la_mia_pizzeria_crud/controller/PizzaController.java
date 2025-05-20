package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired 
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzas = pizzaService.findAll();
        
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }
    

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        return "pizzas/show";

    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = pizzaService.findByName(name);
        model.addAttribute("pizzas", pizzas);
        
        return "pizzas/index";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/create";
        }

        pizzaService.create(formPizza);

        return "redirect:/pizzas";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/edit";
        }

        pizzaService.update(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Pizza pizza = pizzaService.getById(id);
        for (Offer offerToDelete : pizza.getOffers()){

            offerRepository.delete(offerToDelete);
        }
        pizzaService.delete(pizza);
        
        return "redirect:/pizzas";
    }
    
    @GetMapping("/{id}/offer")
    public String offer(@PathVariable Integer id, Model model) {
        Offer offer = new Offer();
        offer.setPizza(pizzaService.getById(id));

        model.addAttribute("offer", offer);
        return "offers/create-or-edit";
    }
    
    
}
