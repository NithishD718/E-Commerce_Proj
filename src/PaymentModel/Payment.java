package PaymentModel;

import InventoryModel.ProductManagement.AccessManagement.Premium;

import java.security.PublicKey;

public class Payment {

    public static class PayFromCreditCard implements IPayment
    {
        private String creditCardNo;

        public PayFromCreditCard(String creditCardNo)
        {
            this.creditCardNo = creditCardNo;
        }

        @Override
        public void pay(double price) {

        }
    }

    public static class PayFromUPI implements IPayment{
        private String upiId;

        public PayFromUPI(String upiId)
        {
            this.upiId = upiId;
        }

        @Override
        public void pay(double price) {

        }
    }

    public static class PayFromBanking implements IPayment{
        private String accountName;

        public PayFromBanking(String accountName)
        {
            this.accountName = accountName;
        }

        @Override
        public void pay(double price) {

        }
    }
    @Premium
    public static class PayByCOD implements IPayment{

        @Override
        public void pay(double price) {

        }
    }
}
