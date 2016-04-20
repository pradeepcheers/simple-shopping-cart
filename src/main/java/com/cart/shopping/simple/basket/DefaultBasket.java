package com.cart.shopping.simple.basket;

import com.cart.shopping.simple.item.Item;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@code Basket}
 */
public class DefaultBasket implements Basket {

    private Collection<Item> items;

    private double cost;

    public DefaultBasket(Collection<Item> items) {
        this.items = items;

        if (items.size() > 0)
            cost = calculateCost();
    }

    @Override
    public void addItems(Item... items) {
        Objects.requireNonNull(items);

        this.items.addAll(Arrays.stream(items).collect(Collectors.toList()));
        cost = calculateCost();
    }

    @Override
    public void removeItems(Item... items) {
        Objects.requireNonNull(items);

        this.items.removeAll(Arrays.stream(items).collect(Collectors.toList()));
        cost = calculateCost();
    }

    @Override
    public Collection<Item> getItems() {
        return items;
    }

    @Override
    public double getCost() {
        return cost;
    }

    private double calculateCost() {
        return items.stream().collect(Collectors.summingDouble(Item::getCost));
    }
}
