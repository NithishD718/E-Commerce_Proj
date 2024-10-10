package PurchaseModel.OrderManagement;

import InventoryModel.ProductManagement.Product;
import PurchaseModel.Item;

import java.util.List;

public class Order {
    private int orderId;
    private List<Item> orderedProducts;

    public Order(int orderId,List<Item> orderedProducts)
    {
        this.orderId = orderId;
        this.orderedProducts = orderedProducts;
    }

    public List<Item> getOrderedProducts()
    {
        return orderedProducts;
    }

    public int getOrderId()
    {
        return orderId;
    }
}

