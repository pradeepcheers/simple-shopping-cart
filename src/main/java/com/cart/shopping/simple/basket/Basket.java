package com.cart.shopping.simple.basket;

import com.cart.shopping.simple.item.Item;

import java.util.Collection;

/**
 * Represents a basket where {@link Item}s can be added, removed.
 * The total cost will be updated upon add / removal of an {@code Item}
 */
public interface Basket {

    /**
     * Adds an {@code Item}s into the {@code Basket}
     * @param items
     */
    void addItems(Item... items);

    /**
     * Removes an {@code Item}s from the {@code Basket}
     * @param items
     */
    void removeItems(Item... items);

    /**
     * Retrieves the {@code Item}s from the basket
     * @return a collection of {@code Item}s
     */
    Collection<Item> getItems();

    /**
     * Retrieves the total cost of {@code Item}s in the basket
     * @return the total cost of {@code Item}s in the basket
     */
    double getCost();
}
