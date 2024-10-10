package InventoryModel.InventoryManagement;

import InventoryModel.ProductManagement.Product;

public class Stock {

    public static boolean isProductStockAvailable(Product product , int quantity)
    {
      return product.stock >= quantity;
    }
}
