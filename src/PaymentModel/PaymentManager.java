package PaymentModel;

import InventoryModel.ProductManagement.AccessManagement.AccessChecker;
import Util.GlobalScanner;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PaymentManager {
  private static Scanner scan = GlobalScanner.getInstance();
  private static int transactionIdGenerator = 15463423;
    public static Transaction managePayment(double price) {

        System.out.println("Choose the payment Method");
        System.out.println("1 -> CreditCard || 2 ->UPI || 3 ->NetBanking || 4 -> Cash On Delivery (Premium)");
        int paymentChoice = scan.nextInt();
        Transaction transaction = new Transaction(transactionIdGenerator++,price, LocalDateTime.now());
        IPayment payment = null;
        switch (paymentChoice)
        {
            case 1 ->{
                System.out.println("Enter the credit card no:");
                 String cardNo = scan.next();
                 payment = new Payment.PayFromCreditCard(cardNo);
                transaction.setPaymentMethod(PaymentMethod.CreditCard);
            }
            case 2 ->{
                System.out.println("Enter the UPI Id:");
                String id = scan.next();
                payment = new Payment.PayFromUPI(id);
                transaction.setPaymentMethod(PaymentMethod.UPI);
            }
            case 3 ->{
                System.out.println("Enter the Account Name");
                String acc = scan.next();
                payment = new Payment.PayFromBanking(acc);
                transaction.setPaymentMethod(PaymentMethod.NetBanking);
            }
            case 4 ->{
               payment = new Payment.PayByCOD();
               try {
                   Class<?> clazz = payment.getClass();
                   if(!AccessChecker.checkPremiumAccessForType(clazz));
                   payment = null;

               }
               catch (Exception e)
               {
                   e.printStackTrace();
               }
               transaction.setPaymentMethod(PaymentMethod.COD);
            }
            default -> System.out.println("Invalid Choice");
        }
        if(payment!=null) {
            payment.pay(price);
            System.out.println("Payment Successfull");
            return transaction;
        }
        else {
            System.out.println("Payment Failed");
            return null;
        }
    }

    public enum PaymentMethod{
        CreditCard,
        UPI,
        NetBanking,
        COD
    }
}
