package BusinessLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLogic.AdminUserValidator;
import PresentationLayer.AdministratorFrame;
import PresentationLayer.Controller;
import WritingIntoCSV.AppendFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

      Controller controller = new Controller();

     /*    DeliveryService deliveryService = new DeliveryService();

         BaseProduct baseProduct = new BaseProduct("Nume",1.2,2,3,4,5,5);*/

      //  System.out.println(deliveryService.isWellFormed(baseProduct));

/*

          DeliveryService deliveryService = new DeliveryService();

          BaseProduct baseProduct = new BaseProduct("Fresh Corn Tortillas ",3.75,23,1,2,61,79);
*/

        //System.out.println(deliveryService.getBaseProductList().contains(baseProduct));

        /*System.out.println(deliveryService.getBaseProductList().size());*/

     /*    DeliveryService deliveryService = new DeliveryService();*/

        /*System.out.println(deliveryService.findProductWithRating(3.75));*/





       /* List<String[]> dataLines = new ArrayList<>();

        dataLines.add(new String[]{"ProdusNou","1.22","5","5","5","5","5"});

        AppendFile appendFile = new AppendFile();

        appendFile.givenDataArray_whenConvertToCSV_thenOutputCreated(dataLines);*/



       /*

*/

       /* String username = "admin";
        String password = "adminUser";

        AdminUserValidator adminUserValidator = new AdminUserValidator();

        System.out.println(adminUserValidator.valideaza(username,password));*/




     /*  // JOptionPane.showMessageDialog(null,"InsertInfo");

        String str = JOptionPane.showInputDialog(null,"Insert Info");

        System.out.println(str  );
*/
    /*    Path path = Path.of("src","main","resources","products.csv");

        Files.lines(path)
                .skip(1)
                .map(line->{

                    String[] fields = line.split(",");
                    return new BaseProduct(fields[0],Double.parseDouble(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]),Integer.parseInt(fields[4]),Integer.parseInt(fields[5]),Integer.parseInt(fields[6]));


                        }

                ).forEach(System.out::println);


*/

      /*  Date date1 = new Date();

        Date date2 = new Date();

        Order order1 = new Order(1,date1);

        Order order2 = new Order(2,date2);

        BaseProduct baseProduct3 = new BaseProduct("Cartof",2.0,200,30,10,9,10);

        BaseProduct baseProduct4 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);

        BaseProduct baseProduct5 = new BaseProduct("Mici",2.0,200,30,10,9,10);


        CompositeProduct compositeProduct = new CompositeProduct("daily1");

        compositeProduct.addBaseProduct(baseProduct4);

        compositeProduct.addBaseProduct(baseProduct5);*/

 /*       System.out.println(compositeProduct.toString());*/




/*
        final List<MenuItem> list = new ArrayList<>();

        list.add(baseProduct3);

        list.addAll(compositeProduct.getBaseProductList());

       // System.out.println(list.toString());

        HashMap<Integer,List<MenuItem>> hashMap = new HashMap<>();

        hashMap.put(1,list);

        System.out.println( hashMap.get(1).toString());*/

/*


        HashMap<Order,MenuItem> hashMap = new HashMap<>();


*/



/*
        HashMap<Integer, List<MenuItem>> orderListHashMap = new HashMap<>();


        MenuItem[] menuItems = new MenuItem[100];

        menuItems[0]=baseProduct3;

        menuItems[1]=baseProduct4;
       orderListHashMap.put(order1.hashCode(), Arrays.stream(menuItems).toList());*/

       /* AbstractList<MenuItem> menuItems = null;*/

        /*List<BaseProduct> baseProductList = new ArrayList<>();

        baseProductList.add(baseProduct3);*/

    /*    menuItems.*/


/*


        System.out.println(menuItems.toString());*/


     /*   BaseProduct baseProduct1 = new BaseProduct("Cartof",2.0,200,30,10,9,10);

        BaseProduct baseProduct2 = new BaseProduct("Cartof",2.0,200,30,10,9,10);

        BaseProduct baseProduct3 = new BaseProduct("Cartof",2.0,200,30,10,9,10);

        BaseProduct baseProduct4 = new BaseProduct("Cartof",2.0,200,30,10,9,10);

        CompositeProduct compositeProduct1 = new CompositeProduct();

        CompositeProduct compositeProduct2= new CompositeProduct();

        compositeProduct1.addBaseProduct(baseProduct1);
        compositeProduct1.addBaseProduct(baseProduct2);

        compositeProduct2.addBaseProduct(baseProduct3);
        compositeProduct2.addBaseProduct(baseProduct4);

        System.out.println(compositeProduct1.hashCode());

        System.out.println(compositeProduct2.hashCode());*/

/*

        BaseProduct baseProduct1 = new BaseProduct("Cartof",2.0,200,30,10,9,10);

        BaseProduct baseProduct2 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);

        BaseProduct baseProduct3 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);

        BaseProduct baseProduct4 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);

        BaseProduct baseProduct5 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);

        BaseProduct baseProduct6 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);
        BaseProduct baseProduct7 = new BaseProduct("Cartofi",2.0,200,30,10,9,10);




        HashSet<BaseProduct> baseProductHashSet = new HashSet<>();

        baseProductHashSet.add(baseProduct1,baseProduct2);



        System.out.println(baseProductHashSet);
*/


        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date1 = new Date();

        Date date2 = new Date();

        Order order1 = new Order(1,date1);

        Order order2 = new Order(1,date2);

        System.out.println(order1.equals(order2));


        System.out.println((formatter.format(date1)));

        System.out.println((formatter.format(date2)));*/

      /*  HashSet<String> strings = new HashSet<>();

        strings.add("abc");
        strings.add("edf");

        System.out.println(strings.toString());
        strings.add("abc");
        strings.add("edf");

        System.out.println(strings.toString());*/
  /*    BaseProduct baseProduct1 = new BaseProduct("Oua",2.0,2,3,4,5,20);
        BaseProduct baseProduct2 = new BaseProduct("Lapte",1.0,3,34,45,56,250);
        BaseProduct baseProduct3 = new BaseProduct("Ciocolata",2.2,2,3,4,25,230);
        BaseProduct baseProduct4 = new BaseProduct("Unt",2.3,2,3,4,5,240);


        CompositeProduct compositeProduct = new CompositeProduct();

        compositeProduct.addBaseProduct(baseProduct1);
        compositeProduct.addBaseProduct(baseProduct2);
        compositeProduct.addBaseProduct(baseProduct3);
        compositeProduct.addBaseProduct(baseProduct4);

        System.out.println(compositeProduct.computePrice());










       /*  List<Person> personList = new ArrayList<Person>();

         personList.add(new Person("Andrei",100));
         personList.add(new Person("Mircea",300));
         personList.add(new Person("Marius",200));
         personList.add(new Person("Dani",50));
*/
       /* List<Person> sortedList = new ArrayList<>();

        sortedList=personList.stream().sorted(Comparator.comparing(person -> person.name)).collect(Collectors.toList());*/



       /*  List<Person> hundredClub = new ArrayList<>();

         hundredClub=personList.stream().filter(person -> person.bilions>=100).sorted(Comparator.comparing(person -> person.name)).collect(Collectors.toList());

*/



   /*      for(Person p:personList)
         {
             if(p.bilions >=100)
                 hundredClub.add(p);

         }
*/
       // hundredClub.forEach(person -> System.out.println(person.name));

       /* hundredClub.forEach(person -> System.out.println(person.name));
*/    }

  /*  static class Person{

        String name;
        int bilions;

        public Person(String name , int bilions)
        {
            this.name = name;
            this.bilions = bilions;

        }

    }*/

}
