package InventoryModel.InventoryManagement;

import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;

public interface IInventoryManager<T> {

    void addProduct(ProductCategories productCategories, String productName , double productPrice , String brandName , int quantity , String category);
    void updateProduct(ProductCategories productCategories , int productId,String updateChoice,double updatedValue);
    Product getProduct();
    void viewProduct(ProductCategories productCategories , int productId);
    void removeProduct(ProductCategories productCategories , int productId);
    void setDiscount(ProductCategories productCategories , int productId,double discount);
}
