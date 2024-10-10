package PurchaseModel.CartManagement;

import InventoryModel.ProductManagement.Product;
import PurchaseModel.Item;
import PurchaseModel.ItemManager;
import PurchaseModel.OrderManagement.Order;
import PurchaseModel.OrderManagement.OrderManager;
import Util.CacheManager;

import java.util.List;
import java.util.Map;

public class CartManager implements ICart {

    CartConfig cartConfig = CartConfig.getInstance();
    Cart cart = cartConfig.getCart();
    @Override
    public void addToCart(Product product,int quantity) {
        cart.cartItems.put(product.productId,new Item(product,quantity));
        System.out.println("Item added to Cart");
    }

    @Override
    public void removeFromCart(int productId) {
        cart.cartItems.remove(productId);
        System.out.println("Item removed from Cart");
    }

    @Override
    public void updateQuantity(int productId , int quantity) {
       Item cartItem = cart.cartItems.get(productId);
       cartItem.setItemQuantity(quantity);
       System.out.println("Item quantity updated");
    }

    @Override
    public void clearCart() {
       cart.cartItems.clear();
       System.out.println("Cart Items Cleared");
    }

    @Override
    public void viewCartItems() {
        System.out.println("Cart Items");
        for (Map.Entry<Integer, Item> itemEntry : cart.cartItems.entrySet()) {
            ItemManager.viewItems(itemEntry.getValue());
        }
    }

    @Override
    public void checkout() {
       List<Item> orderItemList = OrderManager.prepareOrderItemsFromCart(cart);
       System.out.println("Checking out...");
       Order order = OrderManager.createOrder(orderItemList);
       if(order!=null)
        OrderManager.orderCheckout(order);
       clearCart();
    }

    @Override
    public void viewTotalPrice() {
        double totalPrice = 0;
        try {
            for (Map.Entry<Integer, Item> itemEntry : cart.cartItems.entrySet()) {
                totalPrice += itemEntry.getValue().getItem().getPrice();
            }
            System.out.println("Total Price of Items in your Cart : " + totalPrice);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void storeCartConfigInCache(CartConfig config)
    {
       String cartConfigPath = "F:\\Proj\\Console Games\\E-Commerce\\cache\\cartConfig.txt";
        CacheManager cacheManager = new CacheManager(cartConfigPath);
        cacheManager.saveToCache(config);
    }
}
