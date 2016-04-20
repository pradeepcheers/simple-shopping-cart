package com.cart.shopping.simple.inventory;

/**
 * Represents the Inventory Service
 */
public interface Inventory<S> {

    /**
     * Indicates if there is available stock
     * @param s represents an item in the inventory
     * @return boolean representing available stock
     */
    boolean hasStock(S s);

    /**
     * Retrieves the quantity of available stock
     * @param s represents an item in the inventory
     * @return stock quantity
     */
    int getInStockQuantity(S s);

    /**
     * Retrieves the cost of an Item
     * @param s represents an item in the inventory
     * @return cost of an item
     */
    double getCost(S s);
}
