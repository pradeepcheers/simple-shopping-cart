package com.cart.shopping.simple.inventory;

/**
 * Created by Pradeep on 20/04/2016.
 */
public class InventoryRequestException extends Throwable {

    public static final String OUT_OF_STOCK_MESSAGE = "Requested Item is out of stock";

    public static final String IN_STOCK_QUANTITY_MESSAGE = "Only %s requested items are in stock";

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public InventoryRequestException(String message) {
        super(message);
        this.message = message;
    }
}
