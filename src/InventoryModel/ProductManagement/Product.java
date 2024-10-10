package InventoryModel.ProductManagement;

import Util.Category;

public abstract class Product implements IProduct {
    public int productId;
    public String productName;
    public double price;
    public String brand;
    public String productCategory;
    public boolean isExclusive;
    public int stock;
    public Discount discount;

    public Product(int id, String name , double price , String brand,String productCategory,int productStock)
    {
        this.productId = id;
        this.productName = name;
        this.price = price;
        this.brand = brand;
        this.productCategory = productCategory;
        stock = productStock;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public double getPrice() {
        if(discount!= null && discount.isDiscountApplied())
            price = discount.getDiscountedPrice();
        return price;
    }

    public void setPrice(double newPrice)
    {
        this.price = newPrice;
    }

    public String getBrand(){return brand;}

    public String getCategory()
    {
        return productCategory;
    }

    public void setDiscount(Discount discount)
    {
        this.discount = discount;
    }
    public Discount getDiscount()
    {
        return discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
