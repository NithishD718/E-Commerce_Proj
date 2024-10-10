package PaymentModel.Billing;

import InventoryModel.ProductManagement.Product;
import PaymentModel.Transaction;
import PurchaseModel.Item;
import PurchaseModel.OrderManagement.Order;

public class BillingManager {

private static int billIdGenerator=834;
    public static void generateBill(Order order , Transaction transaction) {
          Bill bill = new Bill(billIdGenerator++ , order.getOrderId(),order.getOrderedProducts(),transaction.getPaidAmount(),transaction.getPaymentMethod(),transaction.getTransactionId(),transaction.getTransactionDateTime());
          printBill(bill);
    }

    public static void printBill(Bill bill)
    {
        System.out.println("----------Bill---------------");
        System.out.println("Bill No: " + bill.getBillId());
        System.out.println("[----------Orders------------]");
        System.out.println("OrderId: " + bill.getOrderId());
        System.out.println("ProductName    ||     Price");
        for(Item item : bill.getOrderedItems()) {
            Product product = item.getItem();
            System.out.println(product.getProductName() + ":  " + product.getPrice());
        }
        System.out.println("--------------------------------");
        System.out.println("Total Price : " + bill.getTotalPurchasedPrice());
        System.out.println("Transaction Details");
        System.out.println("Transaction Id: "+bill.getTransactionId() + "  Transaction Method: " + bill.getPaymentMethod() + " TransactionTime: " + bill.getTransactionDate());
        System.out.println("Thank You for Purchasing. Please visit again");
        System.out.println("--------------------------------");
    }
}
