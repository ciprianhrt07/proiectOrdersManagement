package SerializationPractice;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Main {


    public static void main(String[] args) throws IOException {

        User user = new User();
        user.name = "Mircea";
        user.password=  new Date(12, Calendar.DECEMBER,2009,12,12,12);

        User user1 = new User();
        user.name = "Mircea";
        user.password=  new Date(12, Calendar.DECEMBER,2009,12,12,12);

    //    12-Dec-2009 12:12:12

        ArrayList<User> userArrayList = new ArrayList<>();



        FileOutputStream fileOutputStream = new FileOutputStream("UserInfo.ser");

        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

       userArrayList.add(user);
       userArrayList.add(user1);

       outputStream.writeObject(userArrayList);

        outputStream.close();

        fileOutputStream.close();

        System.out.println("Object info saved ");
        
    }




}
