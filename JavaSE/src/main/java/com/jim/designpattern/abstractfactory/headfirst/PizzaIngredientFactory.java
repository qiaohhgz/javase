package com.jim.designpattern.abstractfactory.headfirst;

public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Clams createClam();
}
