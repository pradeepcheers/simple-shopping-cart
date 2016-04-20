package com.cart.shopping.simple.basket

import com.cart.shopping.simple.inventory.Inventory
import com.cart.shopping.simple.inventory.TestInventory
import com.cart.shopping.simple.item.Fruit
import com.cart.shopping.simple.item.FruitItem
import com.cart.shopping.simple.item.Item
import spock.lang.Specification

/**
 * Created by Pradeep on 19/04/2016.
 */
class BasketSpec extends Specification {

    Basket basket;

    Item item;

    Inventory inventory;

    void setup() {
        inventory = new TestInventory();
        item = new FruitItem(inventory, Fruit.BANANAS, 10)
    }

    def "add an item into basket"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
        when:
            basket.addItems(item);
        then:
            basket.getItems().size() == 1
        and:
            item == basket.getItems().find()
    }

    def "remove an item from basket"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
            basket.addItems(item)
        when:
            basket.removeItems(item)
        then:
            basket.getItems().size() == 0
    }

    def "remove an item from empty basket"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
        when:
            basket.removeItems(item)
        then:
            basket.getItems().size() == 0
    }

    def "get items from basket"() {
        given:
            Item apple = new FruitItem(inventory, Fruit.APPLES, 10)
            basket = new DefaultBasket(new ArrayList<Item>())
            basket.addItems(item)
            basket.addItems(apple)
        when:
            Collection<Item> items = basket.getItems()
        then:
            items.size() == 2
        and:
            items.containsAll(item, apple)
    }

    def "get cost of an empty basket"() {
        when:
            basket = new DefaultBasket(new ArrayList<Item>())
        then:
            basket.getCost() == 0d
    }

    def "get cost of basket"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
        when:
            basket.addItems(item)
        then:
            basket.getCost() == 1d
    }

    def "get cost of basket for multiple items"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
        when:
            basket.addItems(item)
            basket.addItems(new FruitItem(inventory, Fruit.APPLES, 10))
        then:
            basket.getCost() == 5d
    }

    def "cost updated an item is added"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
            basket.addItems(item)
        expect:
            basket.getCost() == 1d
        when:
            basket.addItems(new FruitItem(inventory, Fruit.APPLES, 10))
        then:
            basket.getCost() == 5d
    }

    def "cost updated an item is removed"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
            basket.addItems(item)
            basket.addItems(new FruitItem(inventory, Fruit.APPLES, 10))
        expect:
            basket.getCost() == 5d
        when:
            basket.removeItems(item)
        then:
            basket.getCost() == 4d
    }

    def "cost of basket when same item is added multiple items"() {
        given:
            basket = new DefaultBasket(new ArrayList<Item>())
        when:
            basket.addItems(item)
            basket.addItems(item)
        then:
            basket.getCost() == 2d
    }
}
