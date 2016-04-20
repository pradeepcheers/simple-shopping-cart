package com.cart.shopping.simple.item;

/**
 * Represents an entity that can be added, removed from the {@code Basket}
 */
public interface Item<S> {

    /**
     * Retrieves the type / details of an Item
     * @return S
     */
    S getDetails();

    /**
     * Retrieves the cost the {@code Item}
     * @return the cost of {@code Item}
     */
    double getCost();

    /**
     * Retrieves the quantity of {@code Item}
     * @return
     */
    int getQuantity();
}
