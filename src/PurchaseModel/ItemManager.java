package PurchaseModel;

public class ItemManager {

    public static void viewItems(Item item)
    {
        try {
            System.out.println("---------------------------------");
            System.out.println("ProductId: " + item.getItem().productId);
            System.out.println("Product Name: " + item.getItem().getProductName());
            System.out.println("Price: " + item.getItem().getPrice());
            System.out.println("Quantity: " + item.getItemQuantity());
            System.out.println("-----------------------------------");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
