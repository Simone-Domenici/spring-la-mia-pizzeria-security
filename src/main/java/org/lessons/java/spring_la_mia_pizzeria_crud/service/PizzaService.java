package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OfferRepository offerRepository;

    public List<Pizza> findAll(){
        return pizzaRepository.findAll();
    }

    public List<Pizza> findAllSortedByName(){
        return pizzaRepository.findAll(Sort.by("name"));
    }

    public Pizza getById(Integer id){
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        return pizzaOptional.get();
    }

    public List<Pizza> findByName(String name){
        return pizzaRepository.findByNameContainingIgnoreCase(name);

    } 

    public Pizza create(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza){
        for (Offer offerToDelete : pizza.getOffers()){

            offerRepository.delete(offerToDelete);
        }
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id){
        Pizza pizza = pizzaRepository.findById(id).get();

        for (Offer offerToDelete : pizza.getOffers()){

            offerRepository.delete(offerToDelete);
        }
        pizzaRepository.delete(pizza);
    }

    public Boolean existsById(Integer id){
        return pizzaRepository.existsById(id);
    }

    public Boolean exists(Pizza pizza){
        return existsById(pizza.getId());
    }
}
