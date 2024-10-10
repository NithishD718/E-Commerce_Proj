package InventoryModel.ProductManagement;

import InventoryModel.ProductManagement.AccessManagement.Premium;
import PurchaseModel.PurchaseManager;

import java.util.List;
import java.util.Map;

public class ProductView {

    public static void viewAllProducts(ProductCategories productCategories,boolean isFilterApplied , List<FilterCriteria> filterlist )
    {
        Map<Integer, Product> producList = productCategories.productDetails;
        if(isFilterApplied && filterlist!=null)
            producList = ProductFilter.applyFilter(productCategories.productDetails,filterlist);

      System.out.println("------------" + productCategories.categoryName + "------------");
      for(Map.Entry<Integer,Product> entry : producList.entrySet())
      {
          Product product = (Product) entry.getValue();
          if(!product.isExclusive)
            displayProduct(product);
      }
    }

    @Premium
    public static void viewExclusiveProducts(ProductCategories productCategories)
    {
        System.out.println("------------" + "Exclusive" + productCategories.categoryName + "------------");
        for(Map.Entry<Integer,Product> entry : productCategories.productDetails.entrySet())
        {
            Product product = (Product) entry.getValue();
            if(product.isExclusive)
               displayProduct(product);
        }
    }

    public static void displayProduct(Product product)
    {
        System.out.println("id: " + product.productId);
        System.out.println("name: " + product.getProductName());
        Discount discount = product.getDiscount();
        if(discount!= null && discount.isDiscountApplied())
        {
           System.out.println("price: " + product.getPrice() + " (" + discount.getDiscountedPrice()*100 + "% discount)");
        }
        System.out.println("price: " + product.getPrice());
        System.out.println("---------------------------");
    }
}
