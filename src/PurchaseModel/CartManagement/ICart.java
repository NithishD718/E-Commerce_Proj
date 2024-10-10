package PurchaseModel.CartManagement;

import InventoryModel.ProductManagement.Product;

public interface ICart {
    void addToCart(Product product,int quantity);
    void removeFromCart(int productId);
    void updateQuantity(int productId , int quantity);
    void clearCart();
    void viewCartItems();
    void checkout();
    void viewTotalPrice();

}
