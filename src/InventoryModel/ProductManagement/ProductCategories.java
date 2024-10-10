package InventoryModel.ProductManagement;

import InventoryModel.Electronics.ElectronicProduct;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductCategories {
    public int categoryId;
    public String categoryName;
    public int stockCount=0;
    public HashMap<Integer,Product> productDetails = new HashMap<>();

    public ProductCategories(int categoryId, String categoryName)
    {
        this.categoryId = categoryId;
        this.categoryName =categoryName;
    }

    public Product getProduct(int productId)
    {
            return productDetails.get(productId);
    }

    public enum ProductCatgory{
        Electronics,
        Grossery
    }
}
