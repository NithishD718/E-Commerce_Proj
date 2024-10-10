package InventoryModel.ProductManagement;

public class Discount {

    private boolean isDiscountApplied;
    private double discountPercent;
    private double discountedPrice;

    public Discount(double discountPercent, boolean isDiscountApplied, double discountedPrice) {
        this.discountPercent = discountPercent;
        this.isDiscountApplied = isDiscountApplied;
        this.discountedPrice = discountedPrice;
    }

    public boolean isDiscountApplied() {
        return isDiscountApplied;
    }

    public void setDiscountApplied(boolean discountApplied) {
        isDiscountApplied = discountApplied;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
