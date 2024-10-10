package AuthenticationModel;

import AuthenticationModel.AccountManagement.Account;

public class AuthenticationContext {

    private static AuthenticationContext instance;
    private Account currentUser;


    public AuthenticationContext(){}

    public static synchronized AuthenticationContext getInstance()
    {
        if(instance == null) {
            instance = new AuthenticationContext();
        }
        return instance;
    }

    public boolean isAuthenticated()
    {
        return currentUser != null;
    }

    public void setLoginUser(Account user)
    {
        this.currentUser = user;
    }

    public Account getCurrentUser()
    {
          return currentUser;
    }

    public void logoutUser()
    {
        this.currentUser = null;
    }

}
