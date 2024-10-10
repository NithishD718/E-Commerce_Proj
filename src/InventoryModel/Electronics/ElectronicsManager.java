package InventoryModel.Electronics;

import InventoryModel.InventoryManagement.InventoryManager;
import InventoryModel.ProductManagement.Discount;
import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;
import PurchaseModel.PurchaseManager;
import Util.ExceptionUtil.ProductException;
import Util.GlobalScanner;

import java.util.HashMap;
import java.util.Scanner;

public class ElectronicsManager extends InventoryManager<ElectronicsManager> {

    public static Scanner scan = GlobalScanner.getInstance();
    public static int productIdCounter = 1;
    @Override
    public void addProduct(ProductCategories productCategories, String productName , double productPrice , String brandName , int quantity , String category) {
          try {
              System.out.println("Set the warranty Period");
              int warrantyYears = scan.nextInt();
              System.out.println("Enter the batteryLife of the product (in hours)");
              int batteryLife = scan.nextInt();
                  int productId = productIdCounter++;
                  ElectronicProduct electronicProduct = new ElectronicProduct(productId, productName, warrantyYears, productPrice, brandName, batteryLife,category,quantity);
                  productCategories.productDetails.put(productId, electronicProduct);
                  productCategories.stockCount++;
              System.out.println("Product Added Successfully");
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
    }

    @Override
    public void updateProduct(ProductCategories productCategories , int productId,String updateChoice ,double updatedValue) {
        try {
            Product product = productCategories.getProduct(productId);
            if (product == null) throw new ProductException("Product Not Found");
            if (updateChoice.equalsIgnoreCase("price")) {
                product.setPrice(updatedValue);
                System.out.println("Product Price Updated Successfully");
            } else if (updateChoice.equalsIgnoreCase("quantity")) {
                if (updatedValue != 0) {
                    product.stock = (int) updatedValue;
                    System.out.println("Product Quantity Updated Successfully");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public Product getProduct() {
        return null;
    }

    @Override
    public void viewProduct(ProductCategories productCategories, int productId) {
        ElectronicProduct product = (ElectronicProduct) productCategories.getProduct(productId);
        if(product == null) throw new ProductException("Product Not Found");
        displayProductDetails(product);
    }

    @Override
    public void removeProduct(ProductCategories productCategories , int productId) {
        productCategories.productDetails.remove(productId);
        productCategories.stockCount --;
    }

    @Override
    public void setDiscount(ProductCategories productCategories, int productId ,double discount) {
        Product product = productCategories.getProduct(productId);
        double discountedPrice = PurchaseManager.getDiscountedPrice(product.getPrice(),discount);
        product.setDiscount(new Discount(discount,true,discountedPrice));
        System.out.println("Discount Set Successfully");
    }


    public void displayProductDetails(ElectronicProduct product)
    {
        System.out.println("------------------------------------");
        System.out.println("Product Category: " + getCategory(product));
        System.out.println("Product Id: " +product.productId);
        System.out.println("Product Name: " + product.productName);
        System.out.println("Product Price: " + product.price);
        System.out.println("Product Brand: " +product.brand);
        System.out.println("Product Warranty: " +product.warrantyYears);
        System.out.println("Product BatteryLife: " +product.batteryUsageInHours);
        System.out.println("------------------------------------");
    }

    @Override
    public String getCategory(Product product) {
        return product.getCategory();
    }

    @Override
    public <E extends Enum<E>> void viewCategory(Class<E> categorylist) {
         super.viewCategory(categorylist);
    }

}
