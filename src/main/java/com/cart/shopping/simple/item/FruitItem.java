package com.cart.shopping.simple.item;

import com.cart.shopping.simple.inventory.Inventory;
import com.cart.shopping.simple.inventory.InventoryRequestException;

/**
 * Implementation of {@code Item}
 */
public class FruitItem implements Item<Fruit> {

    public static final String NON_NEGATIVE_QUANTITY_EXCEPTION_MESSAGE = "Quantity cannot be negative or zero";

    private Fruit fruit;

    private double cost;

    private int quantity;

    public FruitItem(Inventory<Fruit> inventory, Fruit fruit, int quantity) throws InventoryRequestException {
        checkWithInventory(inventory, fruit, quantity);

        this.fruit = fruit;
        this.quantity = quantity;
        this.cost = inventory.getCost(fruit) * quantity;
    }

    @Override
    public Fruit getDetails() {
        return fruit;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    private void checkWithInventory(Inventory<Fruit> inventory, Fruit fruit, int quantity) throws InventoryRequestException {
        if(quantity <= 0)
            throw new IllegalArgumentException(NON_NEGATIVE_QUANTITY_EXCEPTION_MESSAGE);

        if(!inventory.hasStock(fruit))
            throw new InventoryRequestException(InventoryRequestException.OUT_OF_STOCK_MESSAGE);

        int stockQuantity = inventory.getInStockQuantity(fruit);

        if(quantity > stockQuantity) {
            String exceptionMessage = stockQuantity > 0 ?
                    String.format(InventoryRequestException.IN_STOCK_QUANTITY_MESSAGE, String.valueOf(stockQuantity)) :
                    InventoryRequestException.OUT_OF_STOCK_MESSAGE;

            throw new InventoryRequestException(exceptionMessage);
        }
    }
}
