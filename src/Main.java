import PurchaseModel.CartManagement.CartConfig;

import java.nio.file.attribute.UserPrincipalNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws UserPrincipalNotFoundException {
        // store.openStore();
         MyStore myStore = new MyStore();
         myStore.welcomePage();
    }
}