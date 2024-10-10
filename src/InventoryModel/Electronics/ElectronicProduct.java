package InventoryModel.Electronics;

import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;

public class ElectronicProduct extends Product {

   public int warrantyYears;
   public int batteryUsageInHours;

    public ElectronicProduct(int id, String name , int warrantyYears , double price , String brand , int batteryHours, String productCatgory,int productStock)
    {
        super(id, name, price,brand,productCatgory,productStock);
        this.warrantyYears = warrantyYears;
        this.batteryUsageInHours = batteryHours;
    }


}
