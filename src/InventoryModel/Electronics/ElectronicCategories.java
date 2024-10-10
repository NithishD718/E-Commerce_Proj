package InventoryModel.Electronics;

import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;

public class ElectronicCategories extends ProductCategories {

    public ElectronicCategories(int categoryId, String categoryName) {
        super(categoryId, categoryName);
    }

    @Override
    public ElectronicProduct getProduct(int productId){
           Product product = super.getProduct(productId);
           if(product instanceof ElectronicProduct)
               return (ElectronicProduct) product;
           else {
               System.out.println("Product Not found");
               return null;
           }
    }

    public enum ElectronicsCategory{
        Laptop(1),
        Mobile(2),
        HeadSet(3);
        final int value;
        ElectronicsCategory(int value) {
           this.value = value;
        }

        public int getEnumInteger()
        {
            return value;
        }

        public static ElectronicsCategory getEnum(int value)
        {
            for(ElectronicsCategory category : ElectronicsCategory.values())
            {
                if(category.getEnumInteger() == value)
                    return category;
            }
            throw new IllegalArgumentException("Invalid Category value " + value);
        }
    }

}
