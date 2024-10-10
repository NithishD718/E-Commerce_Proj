package AuthenticationModel.AccountManagement;

import InventoryModel.ProductManagement.AccessManagement.Premium;
import InventoryModel.ProductManagement.Product;

import java.util.List;

public class BuyerAccount extends Account {

    private UserRole role;
    private List<Product> wishedList;

    public BuyerAccount(String username, String password,long mobile) {
        super(username, password, mobile,AccountType.BUYER);
    }

    @Override
    public AccountStatus getAccountStatus() {
        return super.getAccountStatus();
    }

    public void setRole(String userRole)
    {
        this.role = userRole.equalsIgnoreCase(UserRole.Standard.name()) ? UserRole.Standard : UserRole.Premium;
    }

    public UserRole getRole()
    {
        return role;
    }

    @Premium
    public void addToWishList(Product product)
    {
        wishedList.add(product);
    }

    @Premium
    public List<Product> getWishedList()
    {
        return wishedList;
    }


    public enum UserRole
    {
        Standard,
        Premium,
    }
}
