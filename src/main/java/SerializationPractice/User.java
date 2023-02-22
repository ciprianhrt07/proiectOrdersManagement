package SerializationPractice;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    public String name ;
    public Date password;

    private static final long serialVersionUID = 1234567L;

    public void printHello()
    {
        System.out.println("Hello "+name);
    }

}
