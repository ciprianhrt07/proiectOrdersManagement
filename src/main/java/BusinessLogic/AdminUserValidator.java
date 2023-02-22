package BusinessLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminUserValidator  implements  Validator{

    private  static final String userAdminPattern = "admin";

    private  static final String passwordAdminPattern = "adminUser"+ "([\\d]{1,})";


    @Override
    public boolean valideaza(Object o, Object o2) {

      String userName = (String) o;
      String password = (String) o2;

        Pattern pattern = Pattern.compile(passwordAdminPattern);
        Matcher matcher = pattern.matcher(password);

        if(matcher.matches()&&userAdminPattern.equals(userName))
            return true;

              else

            return false;

    }
}
