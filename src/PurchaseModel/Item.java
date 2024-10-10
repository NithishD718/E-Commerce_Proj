package PurchaseModel;

import InventoryModel.ProductManagement.Product;

public class Item {

    private Product item;
    private int itemQuantity;

    public Item(Product item , int itemQuantity)
    {
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public void setItem(Product item)
    {
        this.item = item;
    }

    public void setItemQuantity(int quantity)
    {
        this.itemQuantity = quantity;
    }

    public Product getItem()
    {
        return item;
    }

    public int getItemQuantity()
    {
        return itemQuantity;
    }
}

