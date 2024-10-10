package InventoryModel.Grossery;

import InventoryModel.ProductManagement.ProductCategories;

public class GrosseryCategories extends ProductCategories {


    public GrosseryCategories(int categoryId, String categoryName) {
        super(categoryId, categoryName);
    }

    public enum GrosseryCategory {
        Snacks,
        Beverage,
        Fruits,
        Vegetables
    }
}
