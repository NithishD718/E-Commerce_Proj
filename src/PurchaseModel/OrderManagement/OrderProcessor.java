package PurchaseModel.OrderManagement;

import InventoryModel.InventoryManagement.InventoryManager;
import InventoryModel.ProductManagement.Product;
import InventoryModel.ProductManagement.ProductCategories;
import PaymentModel.Billing.BillingManager;
import PaymentModel.Transaction;
import PurchaseModel.Item;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderProcessor{

    public static BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static volatile boolean running = false;

    public static void processOrders(Transaction transaction)
    {
        int numberOfThreads = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        running = true;
        for(int i=0 ; i<numberOfThreads ; i++)
        {
            executorService.submit(() ->{

                  while(running){
                      try {
                          Order order = orderQueue.take();
                          if(executeProcess(order)) {
                              Thread.sleep(5000);
                              completeOrder(order, transaction);
                          }
                          else
                              System.out.println("Order Processing Failed");
                          if(orderQueue.isEmpty())
                              running =false;
                      } catch (InterruptedException e) {
                          Thread.currentThread().interrupt(); //handle interruption
                          System.out.println("Order Processing Failed");
                      }
                  }
            }
            );
        }
        executorService.shutdown();
    }

    public static boolean executeProcess(Order order)
    {
        try {

            for (Item item : order.getOrderedProducts()) {
                if (item != null) {
                    Product product = item.getItem();
                    ProductCategories productCategory = InventoryManager.getProductCatgory(product.getCategory());
                    if (product.getStock() >= item.getItemQuantity()) {
                        product.setStock(product.getStock() - item.getItemQuantity()); //Reduce the stock count for that individual product
                        if (product.getStock() <= 0) {
                            productCategory.productDetails.remove(product);
                            productCategory.stockCount--; //Reduce the stock count of that category if a specific product model is out of stock.
                        }
                        return true;
                    } else
                        System.out.println("No stock available");
                } else
                    System.out.println("Item Not found");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void completeOrder(Order order,Transaction transaction)
    {
        System.out.println("Order: " + order.getOrderId() + "has been successfully placed.");
        BillingManager.generateBill(order,transaction);
    }
}
