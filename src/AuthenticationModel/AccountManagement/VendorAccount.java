package AuthenticationModel.AccountManagement;

public class VendorAccount extends Account {
    private String supplyProductCategory;
    private static int vendorId =0;
    private String vendorName;


    public VendorAccount(String username, String password , long mobile, String supplyProductCategory, String vendorName) {
        super(username, password,mobile,AccountType.VENDOR);
        this.supplyProductCategory = supplyProductCategory;
        this.vendorName = vendorName;
        vendorId ++;
    }

    @Override
    public AccountStatus getAccountStatus() {
        return super.getAccountStatus();
    }

    public String getSupplyProductCategory()
    {
        return supplyProductCategory;
    }


}
