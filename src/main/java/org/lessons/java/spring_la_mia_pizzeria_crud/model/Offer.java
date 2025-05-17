package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class Offer {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank( message = "The title cannot be blank")
    private String title;

    @NotNull( message = "The offer start date cannot be null")
    private LocalDate offerStart;

    @NotNull( message = "The offer end date cannot be null" )
    private LocalDate offerEnd;

    @ManyToOne
    @JoinColumn( name = "pizza_id", nullable = false )
    private Pizza pizza;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getOfferStart() {
        return offerStart;
    }

    public void setOfferStart(LocalDate offerStart) {
        this.offerStart = offerStart;
    }

    public LocalDate getOfferEnd() {
        return offerEnd;
    }

    public void setOfferEnd(LocalDate offerEnd) {
        this.offerEnd = offerEnd;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    
}
