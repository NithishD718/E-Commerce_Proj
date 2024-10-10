package AuthenticationModel.AccountManagement;

public abstract class Account {

    private String username;
    private String password;
    private long mobile;
    private AccountType accountType;
    private AccountStatus accountStatus;

    public Account(String username , String password, long mobile , AccountType accountType)
    {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.accountType = accountType;
    }
    public AccountType getAccountType()
    {
        return accountType;
    }
    public AccountStatus getAccountStatus(){
        return accountStatus;
    }

    public long getMobile()
    {
        return mobile;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public enum AccountType
    {
        VENDOR,
        BUYER
    }
    public enum AccountStatus
    {
       ACTIVE,
       INACTIVE,
       SUSPENDED,
       PENDING
    }
}
