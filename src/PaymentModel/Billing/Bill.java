package PaymentModel.Billing;

import PaymentModel.PaymentManager;
import PurchaseModel.Item;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {
    private int BillId;
    private int orderId;
    private List<Item> orderedItems;
    private  double totalPurchasedPrice;
    private PaymentManager.PaymentMethod paymentMethod;
    private int transactionId;
    private LocalDateTime transactionDate;


    Bill(int billId, int orderId , List<Item> orderedItems , double totalPurchasedPrice , PaymentManager.PaymentMethod paymentMethod, int transactionId , LocalDateTime transactionDate)
    {
        this.BillId = billId;
        this.orderId=orderId;
        this.orderedItems=orderedItems;
        this.totalPurchasedPrice = totalPurchasedPrice;
        this.paymentMethod = paymentMethod;
        this.transactionDate = transactionDate;
        this.transactionId = transactionId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBillId() {
        return BillId;
    }

    public void setBillId(int billId) {
        BillId = billId;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public double getTotalPurchasedPrice() {
        return totalPurchasedPrice;
    }

    public void setTotalPurchasedPrice(double totalPurchasedPrice) {
        this.totalPurchasedPrice = totalPurchasedPrice;
    }

    public PaymentManager.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentManager.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
