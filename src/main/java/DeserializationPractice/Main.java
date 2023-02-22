package DeserializationPractice;

import SerializationPractice.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = null;

        ArrayList<User> rezultat = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream("E:\\copie proiect 4\\UserInfo.ser");

        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

        rezultat = (ArrayList<User>) inputStream.readObject();

        inputStream.close();

        fileInputStream.close();


        System.out.println(rezultat.toString());

        user.printHello();
    }
}
