package InventoryModel.Grossery;

import InventoryModel.InventoryManagement.InventoryManager;
import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;

public class GrosseryManager extends InventoryManager {


    @Override
    public void addProduct(ProductCategories productCategories, String productName , double productPrice , String brandName , int quantity,String category) {

    }

    @Override
    public void updateProduct(ProductCategories productCategories, int productId,String updateChoice ,double updatedValue) {

    }

    @Override
    public Product getProduct() {
        return null;
    }

    @Override
    public void viewProduct(ProductCategories productCategories, int productId) {

    }

    @Override
    public void removeProduct(ProductCategories productCategories, int productId) {

    }

    @Override
    public void setDiscount(ProductCategories productCategories, int productId,double discount) {

    }


    @Override
    public void viewCategory(Class category) {

    }


    @Override
    public String getCategory(Object product) {
        return "";
    }
}
