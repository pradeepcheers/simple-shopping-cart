package com.cart.shopping.simple.inventory

import com.cart.shopping.simple.item.Fruit
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Pradeep on 19/04/2016.
 */
class InventorySpec extends Specification{

    Inventory inventory

    void setup() {
        inventory = new TestInventory()
    }

    def "In stock"() {
        expect:
            inventory.hasStock(Fruit.APPLES)
    }

    def "Out of stock"() {
        expect:
            !inventory.hasStock(Fruit.PEACHES)
    }

    @Unroll
    def "In stock quantity of #fruit = #stock"() {
        expect:
            inventory.getInStockQuantity(fruit) == stock

        where:
            fruit         | stock
            Fruit.APPLES  | 100
            Fruit.BANANAS | 80
            Fruit.LEMONS  | 50
            Fruit.ORANGES | 75
            Fruit.PEACHES | 0
    }

    @Unroll
    def "Cost of #fruit = #cost"() {
        expect:
            inventory.getCost(fruit) == cost

        where:
            fruit         | cost
            Fruit.APPLES  | 0.40d
            Fruit.BANANAS | 0.10d
            Fruit.LEMONS  | 0.25d
            Fruit.ORANGES | 0.20d
            Fruit.PEACHES | 0.15d
    }
}
