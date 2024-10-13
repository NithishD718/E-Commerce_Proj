package PurchaseModel;

import AuthenticationModel.AccountManagement.BuyerAccount;
import AuthenticationModel.AuthenticationContext;
import InventoryModel.Electronics.ElectronicCategories;
import InventoryModel.Grossery.GrosseryCategories;
import InventoryModel.InventoryManagement.Inventory;
import InventoryModel.InventoryManagement.InventoryManager;
import InventoryModel.InventoryManagement.Stock;
import InventoryModel.ProductManagement.AccessManagement.AccessChecker;
import InventoryModel.ProductManagement.FilterCriteria;
import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;
import InventoryModel.ProductManagement.ProductView;
import PurchaseModel.CartManagement.Cart;
import PurchaseModel.CartManagement.CartManager;
import Util.GlobalScanner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseStore {

    public static Scanner scan = GlobalScanner.getInstance();
    public static void openStore()
    {
        System.out.println("-----------Main Page----------");
        System.out.println("Welcome to Purchasing..");
        System.out.println("Choose the category you are gonna explore");
        System.out.println("Electronics || Grossery");
        String globalCategory = scan.next();
        startPurchase(globalCategory);
    }

    public static void startPurchase(String globalCategory) {
        System.out.println("Choose the category of product you want to purchase (Enter the category Number)");
       InventoryManager manager =  Inventory.getManager(globalCategory);
       if(globalCategory.equalsIgnoreCase(ProductCategories.ProductCatgory.Electronics.name())) {
           manager.viewCategory(ElectronicCategories.ElectronicsCategory.class);
       }
       else
       {
           manager.viewCategory(GrosseryCategories.GrosseryCategory.class);
       }
        int categoryId = scan.nextInt();
       ProductCategories productCategories = InventoryManager.productCategoryMap.get(categoryId);
       boolean isExitPurchase = false;
       boolean isFilterApplied = false;
        List<FilterCriteria> filteredList = new ArrayList<>();
        do {
            ProductView.viewAllProducts(productCategories, isFilterApplied, filteredList);
            System.out.println("1 -> Purchase || 2 -> View with Filters || 3 --> View Exclusiive Products (For Premium) || 4 -> View Cart || 5 -> Disable Filters || 6 -> Exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> {  //Purchase
                    System.out.println("Enter the productId");
                    int productId = scan.nextInt();
                    manager.viewProduct(productCategories, productId); //viewing the product with more details and specs
                    System.out.println("1 -> Buy Now || 2 -> Add to Cart || 3 -> Add to Wishlist (Premium)");
                    int actionChoice = scan.nextInt();
                    int quantity=0;
                    if(actionChoice>0 && actionChoice <=2) {
                        System.out.println("Enter the Quantity of the product to add");
                        quantity = scan.nextInt();
                    }
                    Product product = productCategories.getProduct(productId);
                    if(Stock.isProductStockAvailable(product,quantity)) {
                        executeAction(actionChoice, product, productCategories, quantity);
                    }
                    else
                    {
                        System.out.println("Stock Not Available");
                    }
                }
                case 2 -> { // Filter products

                    isFilterApplied = true;
                    System.out.println("price || brand");
                    for(int i=0 ; i<2 ; i++) {
                        System.out.println("Choose the filter you want to apply");
                        String filterType = scan.next();
                        String filterValue = getFilterValue(filterType);
                        filteredList.add(new FilterCriteria(filterType,filterValue));
                    }
                }
                case 3 -> {  //View ExclusiveProducts
                    try {
                        ProductView productView = new ProductView();
                        Method method = productView.getClass().getMethod("viewExclusiveProducts");
                        if(AccessChecker.checkPremiumAccess(method))
                             method.invoke(productView);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                case 4 ->{ //ManageCart
                    CartManager cartManager = new CartManager();
                    Cart.manageCart(cartManager);
                }
                case 5 ->{   //DisableFilter

                }
                case 6 ->{
                    isExitPurchase = true;
                }
            }
        }while (!isExitPurchase || isFilterApplied);
    }

    public static String getFilterValue(String filterType)
    {
         if(filterType.equalsIgnoreCase("price"))
         {
             System.out.println("Enter the filter range: 100-1000 || 1000-5000 || 5000-10000 || 10000-50000");
             return scan.next();
         }
         else if(filterType.equalsIgnoreCase("brand"))
         {
             System.out.println("Enter the brand name:"); //need to handle if the brand available check
             return scan.next();
         }
         return null;
    }

    public static void executeAction(int actionChoice, Product product , ProductCategories productCategories,int quantity)
    {
            switch (actionChoice) {
                case 1 -> {

                    PurchaseManager.buyProduct(product, quantity);
                }
                case 2 -> {
                    CartManager cartManager = new CartManager();
                    cartManager.addToCart(product, quantity);
                    System.out.println("1 -> Continue Purchase || 2 -> View Cart");
                    int choice = scan.nextInt();
                    if (choice == 1)
                        return;
                    else {
                        cartManager.viewCartItems();
                        Cart.manageCart(cartManager);
                    }

                }
                case 3 -> {
                    try {
                        BuyerAccount account = (BuyerAccount) AuthenticationContext.getInstance().getCurrentUser();
                        Method method = account.getClass().getMethod("addToWishList");
                        if (AccessChecker.checkPremiumAccess(method))
                            method.invoke(account);
                        System.out.println("Product Added To WishList");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
