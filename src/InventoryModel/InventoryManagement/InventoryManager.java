package InventoryModel.InventoryManagement;

import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;
import Util.ICategory;

import java.util.HashMap;
import java.util.Map;

public abstract class InventoryManager<T> implements IInventoryManager<T>, ICategory<Product> {

    public static HashMap<Integer,ProductCategories> productCategoryMap = new HashMap<>();

    @Override
    abstract public void addProduct(ProductCategories productCategories, String productName , double productPrice , String brandName  , int quantity, String category);

    @Override
   abstract public void updateProduct(ProductCategories productCategories , int productId ,String updateChoice, double updatedValue);

    @Override
    public  abstract Product getProduct();


    @Override
    public abstract void viewProduct(ProductCategories productCategories, int productId);


    @Override
    public abstract void removeProduct(ProductCategories productCategories , int productId);

    public void getProductStock() {

    }

    public static ProductCategories getProductCatgory(String categoryName)
    {
        for(Map.Entry<Integer,ProductCategories> categoryMap : productCategoryMap.entrySet())
        {
            ProductCategories categories = categoryMap.getValue();
            if(categories.categoryName.equals(categoryName))
                return categories;
        }
        return null;
    }
}
