package BusinessLogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeUserValidator implements Validator{

    private  static final String userClientPattern = "employee";

    private  static final String passwordClientPattern = "employeeUser"+ "([\\d]{1,})";

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
