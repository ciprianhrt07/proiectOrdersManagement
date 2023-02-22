package BusinessLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientUserValidator implements  Validator{

    private  static final String userClientPattern = "client";

    private  static final String passwordClientPattern = "clientUser"+ "([\\d]{1,})";

    @Override
    public boolean valideaza(Object o, Object o2) {
        String userName = (String) o;
        String password = (String) o2;

        Pattern pattern = Pattern.compile(passwordClientPattern);
        Matcher matcher = pattern.matcher(password);

        if(matcher.matches()&&userClientPattern.equals(userName))
            return true;

        else

            return false;
    }
}
