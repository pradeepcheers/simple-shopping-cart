package com.cart.shopping.simple.item

import com.cart.shopping.simple.inventory.Inventory
import com.cart.shopping.simple.inventory.InventoryRequestException
import com.cart.shopping.simple.inventory.TestInventory
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by Pradeep on 19/04/2016.
 */
class ItemSpec extends Specification {

    Item item

    Inventory inventory

    void setup() {
        inventory = new TestInventory()
    }

    def "Item for 0 quantity"() {
        when:
            int quantity = 0
            item = new FruitItem(inventory, Fruit.LEMONS, quantity)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == "Quantity cannot be negative or zero"
    }

    def "Item for a negative quantity"() {
        when:
            int quantity = -2
            item = new FruitItem(inventory, Fruit.PEACHES, quantity)
        then:
            IllegalArgumentException exception = thrown()
            exception.message == "Quantity cannot be negative or zero"
    }

    def "Item for a positive quantity"() {
        when:
            int quantity = 2
            item = new FruitItem(inventory, Fruit.APPLES, quantity)
        then:
            item.getDetails() == Fruit.APPLES
        and:
            item.getQuantity() == quantity
        and:
            item.getCost() == inventory.getCost(Fruit.APPLES) * quantity
    }

    @Unroll
    def "Item for the inventory data of Fruit:#fruit, Cost:#cost and Quantity:#quantity"() {
        when:
            item = new FruitItem(inventory, fruit, quantity)
        then:
            item.getDetails() == fruit
        and:
            item.getQuantity() == quantity
        and:
            item.getCost() == cost * quantity

        where:
            fruit        | cost | quantity
            Fruit.APPLES | 0.40d| 100
            Fruit.BANANAS| 0.10d| 80
            Fruit.LEMONS | 0.25d| 50
            Fruit.ORANGES| 0.20d| 75
    }

    def "Item for out of stock"() {
        when:
            int quantity = 2
            item = new FruitItem(inventory, Fruit.PEACHES, quantity)
        then:
            InventoryRequestException exception = thrown()
            exception.message == InventoryRequestException.OUT_OF_STOCK_MESSAGE
    }

    def "Item in stock but not as many as requested"() {
        when:
            int quantity = 100
            item = new FruitItem(inventory, Fruit.LEMONS, quantity)
        then:
            InventoryRequestException exception = thrown()
            exception.message == String.format(InventoryRequestException.IN_STOCK_QUANTITY_MESSAGE,
                    String.valueOf(inventory.getInStockQuantity(Fruit.LEMONS)))
    }
}
