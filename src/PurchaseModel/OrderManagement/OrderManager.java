package PurchaseModel.OrderManagement;

import PaymentModel.PaymentManager;
import PaymentModel.Transaction;
import PurchaseModel.CartManagement.Cart;
import PurchaseModel.Item;
import PurchaseModel.ItemManager;

import java.util.*;

public class OrderManager {

   private  static int orderIdGenerator = 0;
    public static Order createOrder(List<Item> orderItemList)
    {
        try {
            if (!orderItemList.isEmpty()) {
                Order order = new Order(orderIdGenerator++, orderItemList);
                OrderProcessor.orderQueue.offer(order);
                System.out.println("Order Created Successfully");
                return order;
            } else {
                System.out.println("No items in the order");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Item> prepareOrderItemsFromCart(Cart cart) {
        List<Item> order = new ArrayList<>();
        for(Map.Entry<Integer, Item> itemEntry : cart.cartItems.entrySet() )
        {
            order.add(itemEntry.getValue());
        }
        return order;
    }

    public static void viewOrderDetails(Order order)
    {
        System.out.println("-------Order Summary--------");
        System.out.println("OrderId : " + order.getOrderId());
        for(Item items : order.getOrderedProducts())
        {
            ItemManager.viewItems(items);
        }
        System.out.println("Total Price: " + getOrderPrice(order));
        System.out.println("-----------------------------");
    }

    public static void orderCheckout(Order order)
    {
        viewOrderDetails(order);
       Transaction transaction = PaymentManager.managePayment(getOrderPrice(order));
        System.out.println("Processing your order...");
        OrderProcessor.processOrders(transaction);
    }

    public static double getOrderPrice(Order order)
    {
        List<Item> orderItems = order.getOrderedProducts();
        int totalPrice =0;
       for(Item item : orderItems)
       {
          totalPrice += item.getItem().getPrice();
       }
      return totalPrice;
    }
}
