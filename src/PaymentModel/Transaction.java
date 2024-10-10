package PaymentModel;

import java.text.DateFormat;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private double paidAmount;
    private LocalDateTime transactionDateTime;
    private PaymentManager.PaymentMethod paymentMethod;

    public Transaction(int transactionId,double paidAmount , LocalDateTime transactionDateTime)
    {
        this.transactionId = transactionId;
        this.paidAmount = paidAmount;
        this.transactionDateTime = transactionDateTime;
    }

    public void setPaymentMethod(PaymentManager.PaymentMethod paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public PaymentManager.PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }
    public int getTransactionId()
    {
        return transactionId;
    }

    public double getPaidAmount()
    {
        return paidAmount;
    }

    public LocalDateTime getTransactionDateTime()
    {
        return transactionDateTime;
    }
}
