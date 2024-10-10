package InventoryModel.InventoryManagement;

import AuthenticationModel.AccountManagement.Account;
import AuthenticationModel.AccountManagement.VendorAccount;
import InventoryModel.Electronics.ElectronicCategories;
import InventoryModel.Electronics.ElectronicsManager;
import InventoryModel.Grossery.GrosseryCategories;
import InventoryModel.Grossery.GrosseryManager;
import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;
import Util.ExceptionUtil.CategoryException;
import Util.GlobalScanner;

import java.util.Scanner;

public class Inventory {

    public static Scanner scan = GlobalScanner.getInstance();

    public static void openInventory(Account currentUser)
    {
        VendorAccount account = (VendorAccount) currentUser;
        ProductCategories productCategories = null;
        String category = null;
       System.out.println("Welcome To Your Inventory");
       boolean changeCategory;
       InventoryManager manager = getManager(account.getSupplyProductCategory());
       do {
           System.out.println("Please choose the category of product you want to manage");
           if (account.getSupplyProductCategory().equals(ProductCategories.ProductCatgory.Electronics.name())) {
               manager.viewCategory(ElectronicCategories.ElectronicsCategory.class);
               int categoryId = scan.nextInt();
               category = ElectronicCategories.ElectronicsCategory.getEnum(categoryId).name();
               productCategories = new ElectronicCategories(categoryId, category);
               InventoryManager.productCategoryMap.put(categoryId, productCategories);
           } else {
               manager.viewCategory(GrosseryCategories.GrosseryCategory.class);
               int categoryId = scan.nextInt();
           }
           boolean hasManaged = false;
           while (!hasManaged) {
               System.out.println("1 -> Add Product || 2 -> Update Product || 3 -> Remove Product || 4 -> View Product || 5 -> Set Discount || 6 -> Exit");
               System.out.println("Please select your choice");
               int action = scan.nextInt();
               hasManaged = manageInventory(action, productCategories, manager, category, hasManaged);
           }
           System.out.println("Do you wanna exit back to home (Y)? or choose (N) to continue manage other products");
          Character managePromptInput = scan.next().charAt(0);
         changeCategory = managePromptInput != 'Y';
       }
       while (changeCategory);
    }


    public static InventoryManager getManager(String supplyCategory)
    {
            if (supplyCategory.equals(ProductCategories.ProductCatgory.Electronics.name()))
                return new ElectronicsManager();
            else if(supplyCategory.equals(ProductCategories.ProductCatgory.Grossery.name()))
                return new GrosseryManager();
            else
                throw new CategoryException("Invalid Category!");
    }

    public static boolean manageInventory(int actionId , ProductCategories productCategories,InventoryManager manager, String category , boolean exitManagement)
    {
       switch (actionId)
       {
           case 1 ->{  //Add Product
               System.out.println("Enter the Product name");
               String productName = scan.next();
               System.out.println("Enter the Product price");
               double productPrice = scan.nextDouble();
               System.out.println("Enter the brand name");
               String brandName = scan.next();
               System.out.println("Enter the number of quantity to add");
               int quantity = scan.nextInt();
               manager.addProduct(productCategories,productName,productPrice,brandName,quantity,category);

           }
           case 2 ->{  //Update Product
               System.out.println("Enter the product ID");
               int productId = scan.nextInt();
               System.out.println("Update: price || quantity");
               String updateChoice =scan.next();
               System.out.println("Enter the updated Value");
               double updatedValue = scan.nextDouble();
               manager.updateProduct(productCategories,productId,updateChoice , updatedValue);
           }
           case 3->{  //Remove Product
               System.out.println("Enter the product ID");
               int productId = scan.nextInt();
               manager.removeProduct(productCategories ,productId);
           }
           case 4 ->{  //View Product
                System.out.println("Enter the product ID");
                 int productId = scan.nextInt();
                 manager.viewProduct(productCategories,productId);
           }
           case 5->{  //Set Discount
                System.out.println("Enter the product ID");
                int productId = scan.nextInt();
                System.out.println("Enter the discount percent in double");
                double discount = scan.nextDouble();
                manager.setDiscount(productCategories,productId,discount);
           }
           case 6 ->{
                exitManagement = true;
           }
           default -> throw new IllegalStateException("Unexpected value: " + actionId);
       }
      return exitManagement;
    }

}
