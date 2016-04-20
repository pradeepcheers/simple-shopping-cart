package com.cart.shopping.simple.inventory;

import com.cart.shopping.simple.item.Fruit;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@code Inventory}
 */
public class TestInventory implements Inventory<Fruit> {

    private static Set<InventoryDetails> inventory;

    static
    {
        inventory = new HashSet<>();
        inventory.add(new InventoryDetails(Fruit.APPLES, 0.40, 100));
        inventory.add(new InventoryDetails(Fruit.BANANAS, 0.10, 80));
        inventory.add(new InventoryDetails(Fruit.LEMONS, 0.25, 50));
        inventory.add(new InventoryDetails(Fruit.ORANGES, 0.20, 75));
        inventory.add(new InventoryDetails(Fruit.PEACHES, 0.15, 0));
    }

    @Override
    public boolean hasStock(Fruit fruit) {
        return getInventoryDetails(fruit).getStock() > 0;
    }

    @Override
    public int getInStockQuantity(Fruit fruit) {
        return getInventoryDetails(fruit).getStock();
    }

    @Override
    public double getCost(Fruit fruit) {
        return getInventoryDetails(fruit).getCost();
    }

    private InventoryDetails getInventoryDetails(Fruit fruit) {
        return inventory.stream().filter(inventory -> fruit == inventory.getFruit()).findFirst().get();
    }

    private static class InventoryDetails {

        Fruit fruit;

        double cost;

        int stock;

        public InventoryDetails(Fruit fruit, double cost, int stock) {
            this.fruit = fruit;
            this.cost = cost;
            this.stock = stock;
        }

        public Fruit getFruit() {
            return fruit;
        }

        public double getCost() {
            return cost;
        }

        public int getStock() {
            return stock;
        }
    }
}
