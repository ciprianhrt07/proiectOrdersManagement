package PresentationLayer;

import BusinessLayer.*;
import BusinessLayer.MenuItem;
import BusinessLogic.AdminUserValidator;
import BusinessLogic.ClientUserValidator;
import BusinessLogic.EmployeeUserValidator;
import SerializationPractice.User;
import WritingIntoCSV.AppendFile;
import WritingIntoCSV.Serializator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Controller implements Serializable {

    private LogIn logIn;
    private AdministratorFrame administratorFrame;
    private ClientFrame clientFrame;
    private EmployeeFrame employeeFrame;
    private DeliveryService deliveryService;
    private InsertData insertData;
    private Client clientCurentConectat;
    private Serializator serializator;

    public Controller() {

        insertData = new InsertData();
        deliveryService = new DeliveryService();
        logIn = new LogIn();
        administratorFrame = new AdministratorFrame();
        clientFrame = new ClientFrame();
        employeeFrame = new EmployeeFrame();
        serializator = new Serializator();

        deliveryService.addObserver(employeeFrame);

        logIn.getLoginFrame().setVisible(true);

        logIn.getLoginFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                List<Order> orderList = deliveryService.getComenzi().keySet().stream().toList();

                System.out.println(orderList.toString());

                for (Order order : orderList) {


                    try {
                      /*  deliveryService.getOutputStream().writeObject(order.toString()+"\n");
                        deliveryService.getOutputStream().writeObject(deliveryService.getComenzi().get(order).toString()+"\n");*/

                        serializator.getOutputStream().writeObject(order);
                        serializator.getOutputStream().writeObject(deliveryService.getComenzi().get(order));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                try {
                    serializator.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    serializator.getFileOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Object info saved ");
            }
        });

        logIn.getButonConnect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String userName = logIn.getTextUserName().getText();
                String password = String.valueOf(logIn.getPasswordField().getPassword());

                AdminUserValidator adminUserValidator = new AdminUserValidator();
                ClientUserValidator clientUserValidator = new ClientUserValidator();
                EmployeeUserValidator employeeUserValidator = new EmployeeUserValidator();

                if (adminUserValidator.valideaza(userName, password) == true) {
                    administratorFrame.getFrameAdministrator().setVisible(true);
                    citireDate();
                    logIn.getLoginFrame().setVisible(false);

                } else if (clientUserValidator.valideaza(userName, password) == true) {

                    clientFrame.getClientFrame().setVisible(true);
                    Client c = new Client(userName, password);
                    clientCurentConectat = c;
                    logIn.getLoginFrame().setVisible(false);

                } else if (employeeUserValidator.valideaza(userName, password) == true) {
                    employeeFrame.getFrameEmployee().setVisible(true);
                    logIn.getLoginFrame().setVisible(false);
                } else
                    JOptionPane.showMessageDialog(null, "Nu s-a putut identifica un utilizator pentru datele introduse");


            }
        });

        administratorFrame.getButonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                administratorFrame.getFrameAdministrator().setVisible(false);
                logIn.getLoginFrame().setVisible(true);

            }
        });

        clientFrame.getButonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientFrame.getClientFrame().setVisible(false);
                logIn.getLoginFrame().setVisible(true);
            }
        });

        employeeFrame.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeFrame.getFrameEmployee().setVisible(false);
                logIn.getLoginFrame().setVisible(true);
            }
        });

        administratorFrame.getButonImportFromFile().addActionListener(e -> {

            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream("ProductsImport.ser");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            ObjectOutputStream outputStream = null;
            try {
                outputStream = new ObjectOutputStream(fileOutputStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            final String[] outputString = {""};
            outputString[0] = deliveryService.importProducts();


            try {
                outputStream.writeObject(outputString[0]);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //System.out.println("Object info saved ");
            JOptionPane.showMessageDialog(null, "Object info saved ");


            Object[][] objects = new Object[deliveryService.getBaseProductList().size()][7];

            BaseProduct[] baseProducts = new BaseProduct[deliveryService.getBaseProductList().size()];

            baseProducts = deliveryService.getBaseProductList().toArray(new BaseProduct[0]);


            for (int i = 0; i < deliveryService.getBaseProductList().size(); i++) {
                objects[i][0] = baseProducts[i].getTitle();
                objects[i][1] = baseProducts[i].getRating();
                objects[i][2] = baseProducts[i].getCalories();
                objects[i][3] = baseProducts[i].getProtein();
                objects[i][4] = baseProducts[i].getFat();
                objects[i][5] = baseProducts[i].getSodium();
                objects[i][6] = baseProducts[i].getPrice();
            }

            administratorFrame.getTable().setModel(new DefaultTableModel(
                    objects,
                    new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                    }
            ));

              /*  System.out.println(baseProducts.length);

                System.out.println(baseProducts[0].toString());*/


        });

        administratorFrame.getButonAddProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                administratorFrame.getFrameAdministrator().setVisible(false);
                insertData.getFrameInsertData().setVisible(true);

            }
        });

        insertData.getBtnInsert().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = insertData.getTextTitle().getText();
                double rating;
                int calories, fat, protein, price, sodium;

                BaseProduct newBaseProduct = null;

                try {

                    calories = Integer.parseInt(insertData.getTextCalories().getText());
                    rating = Double.parseDouble(insertData.getTextRating().getText());
                    fat = Integer.parseInt(insertData.getTextFat().getText());
                    protein = Integer.parseInt(insertData.getTextProteins().getText());
                    price = Integer.parseInt(insertData.getTextPrice().getText());
                    sodium = Integer.parseInt(insertData.getTextSodium().getText());

                    newBaseProduct = new BaseProduct(name, rating, calories, protein, fat, sodium, price);

                    System.out.println(newBaseProduct);

                    administratorFrame.getFrameAdministrator().setVisible(true);
                    insertData.getFrameInsertData().setVisible(false);

                    //append the file and insert into csv!
                    //for modify and



/*

                        FileWriter pw = new FileWriter("E:\\copie proiect 4\\products.csv",true);
*/

                    List<String[]> dataLines = new ArrayList<>();

                    dataLines.add(new String[]{name, String.valueOf(rating), String.valueOf(calories), String.valueOf(protein), String.valueOf(fat), String.valueOf(sodium), String.valueOf(price)});

                    AppendFile appendFile = new AppendFile();

                    appendFile.givenDataArray_whenConvertToCSV_thenOutputCreated_Append(dataLines);


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid data input for a new base product");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        clientFrame.getButonSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //  System.out.println(deliveryService.getBaseProductList().size());
                List<BaseProduct> baseProductFound = new ArrayList<>();
                Object[][] objects;


                if (clientFrame.getRdbtnRating().isSelected() == true && clientFrame.getRdbtnCalories().isSelected() == false
                        && clientFrame.getRdbtnFat().isSelected() == false && clientFrame.getRdbtnProteins().isSelected() == false
                        && clientFrame.getRdbtnSodium().isSelected() == false && clientFrame.getRdbtnKeyWord().isSelected() == false
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == false


                ) {


                    try {

                        Double rating = Double.parseDouble(clientFrame.getTextSearchProducts().getText());
                        baseProductFound = deliveryService.findProductWithRating(rating);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {

                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }

                } else if (clientFrame.getRdbtnRating().isSelected() == false && clientFrame.getRdbtnCalories().isSelected() == true
                        && clientFrame.getRdbtnFat().isSelected() == false && clientFrame.getRdbtnProteins().isSelected() == false
                        && clientFrame.getRdbtnSodium().isSelected() == false && clientFrame.getRdbtnKeyWord().isSelected() == false
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == false) {

                    try {
                        int calories = Integer.parseInt(clientFrame.getTextSearchProducts().getText());
                        baseProductFound = deliveryService.findProductWithNumberOfCalories(calories);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException exx) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }


                } else if (clientFrame.getRdbtnRating().isSelected() == false && clientFrame.getRdbtnCalories().isSelected() == false
                        && clientFrame.getRdbtnFat().isSelected() == true && clientFrame.getRdbtnProteins().isSelected() == false
                        && clientFrame.getRdbtnSodium().isSelected() == false && clientFrame.getRdbtnKeyWord().isSelected() == false
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == false) {

                    try {
                        int fat = Integer.parseInt(clientFrame.getTextSearchProducts().getText());
                        baseProductFound = deliveryService.findProductWithNumberOfFats(fat);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException exx) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }


                } else if (clientFrame.getRdbtnRating().isSelected() == false && clientFrame.getRdbtnCalories().isSelected() == false
                        && clientFrame.getRdbtnFat().isSelected() == false && clientFrame.getRdbtnProteins().isSelected() == true
                        && clientFrame.getRdbtnSodium().isSelected() == false && clientFrame.getRdbtnKeyWord().isSelected() == false
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == false) {

                    try {
                        int proteins = Integer.parseInt(clientFrame.getTextSearchProducts().getText());
                        baseProductFound = deliveryService.findProductWithNumberOfProteins(proteins);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException exx) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }


                } else if (clientFrame.getRdbtnRating().isSelected() == false && clientFrame.getRdbtnCalories().isSelected() == false
                        && clientFrame.getRdbtnFat().isSelected() == false && clientFrame.getRdbtnProteins().isSelected() == false
                        && clientFrame.getRdbtnSodium().isSelected() == true && clientFrame.getRdbtnKeyWord().isSelected() == false
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == false) {

                    try {
                        int sodium = Integer.parseInt(clientFrame.getTextSearchProducts().getText());
                        baseProductFound = deliveryService.findProductWithNumberOfSodium(sodium);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException exx) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }


                } else if (clientFrame.getRdbtnRating().isSelected() == false && clientFrame.getRdbtnCalories().isSelected() == false
                        && clientFrame.getRdbtnFat().isSelected() == false && clientFrame.getRdbtnProteins().isSelected() == false
                        && clientFrame.getRdbtnSodium().isSelected() == false && clientFrame.getRdbtnKeyWord().isSelected() == false
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == true) {

                    try {
                        int price = Integer.parseInt(clientFrame.getTextSearchProducts().getText());
                        baseProductFound = deliveryService.findProductWithPrice(price);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException exx) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }


                } else if (clientFrame.getRdbtnRating().isSelected() == false && clientFrame.getRdbtnCalories().isSelected() == false
                        && clientFrame.getRdbtnFat().isSelected() == false && clientFrame.getRdbtnProteins().isSelected() == false
                        && clientFrame.getRdbtnSodium().isSelected() == false && clientFrame.getRdbtnKeyWord().isSelected() == true
                        && clientFrame.getRdbtnSearchbyprice().isSelected() == false) {

                    try {
                        String keyWord = clientFrame.getTextSearchProducts().getText();

                        baseProductFound = deliveryService.findProductsWhichContains(keyWord);

                        BaseProduct[] baseProducts = new BaseProduct[baseProductFound.size()];

                        baseProducts = baseProductFound.toArray(new BaseProduct[0]);

                        objects = new Object[baseProductFound.size()][7];

                        for (int i = 0; i < baseProductFound.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    } catch (NumberFormatException exx) {
                        JOptionPane.showMessageDialog(null, "Please insert a valid rating ");
                    }


                } else {
                    List<String> optionsChecked = new ArrayList<>();


                    if (clientFrame.getRdbtnKeyWord().isSelected() == true) {
                        optionsChecked.add("Search by KeyWord");
                    }

                    if (clientFrame.getRdbtnRating().isSelected() == true) {
                        optionsChecked.add("Search by Rating");
                    }

                    if (clientFrame.getRdbtnCalories().isSelected() == true) {
                        optionsChecked.add("Search by Calories");
                    }

                    if (clientFrame.getRdbtnProteins().isSelected() == true) {
                        optionsChecked.add("Search by Proteins");
                    }

                    if (clientFrame.getRdbtnFat().isSelected() == true) {
                        optionsChecked.add("Search by Fat");
                    }

                    if (clientFrame.getRdbtnSodium().isSelected() == true) {
                        optionsChecked.add("Search by Sodium");
                    }

                    if (clientFrame.getRdbtnSearchbyprice().isSelected() == true) {
                        optionsChecked.add("Search by Price");
                    }

                    if (optionsChecked.size() == 0) {
                        JOptionPane.showMessageDialog(null, "No option has been checked");
                    } else {
                        String dataInput = clientFrame.getTextSearchProducts().getText();

                        List<String> items = Arrays.asList(dataInput.split("\\s*,\\s*"));
                        List<BaseProduct> returnedList = new ArrayList<>();

                        System.out.println(optionsChecked.toString());
                        System.out.println(items.toString());

                        returnedList = deliveryService.findProductWithMultipleCriteria(optionsChecked, items);

                        BaseProduct[] baseProducts = new BaseProduct[returnedList.size()];

                        baseProducts = returnedList.toArray(new BaseProduct[0]);

                        objects = new Object[returnedList.size()][7];

                        for (int i = 0; i < returnedList.size(); i++) {
                            objects[i][0] = baseProducts[i].getTitle();
                            objects[i][1] = baseProducts[i].getRating();
                            objects[i][2] = baseProducts[i].getCalories();
                            objects[i][3] = baseProducts[i].getProtein();
                            objects[i][4] = baseProducts[i].getFat();
                            objects[i][5] = baseProducts[i].getSodium();
                            objects[i][6] = baseProducts[i].getPrice();
                        }

                        clientFrame.getTable().setModel(new DefaultTableModel(
                                objects,
                                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                                }
                        ));

                    }

                }


            }
        });


        List<Integer> linie = new ArrayList<>();
        List<Integer> coloana = new ArrayList<>();


        administratorFrame.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            int cnt = 1;

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (cnt % 2 == 1) {
                    int row = administratorFrame.getTable().getSelectedRow();
                    int col = administratorFrame.getTable().getSelectedColumn();

                    if (row >= 0 && col >= 0) {

                        linie.add(row);
                        coloana.add(col);
                    }


                }
                cnt++;
            }
        });


        clientFrame.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            int cnt = 1;

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (cnt % 2 == 1) {
                    int row = clientFrame.getTable().getSelectedRow();
                    int col = clientFrame.getTable().getSelectedColumn();

                    if (row >= 0 && col >= 0) {

                        linie.add(row);
                        coloana.add(col);
                    }


                }
                cnt++;
            }
        });


        administratorFrame.getButonDeleteProduct().addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(linie.toString());
                System.out.println(coloana.toString());

                deliveryService.deleteProduct(linie);


                linie.removeAll(linie);
                coloana.removeAll(coloana);

            }
        });


        administratorFrame.getButonCreateComposedProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


             /*    System.out.println(linie.toString());
                 System.out.println(coloana.toString());*/

                String nume = JOptionPane.showInputDialog(null, "Introduceti Numele Produslui Compus");


                deliveryService.createCompositePorduct(nume, linie);

                linie.removeAll(linie);
                coloana.removeAll(coloana);

            }
        });

        clientFrame.getBtnPlaceorder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              /*  System.out.println(linie.toString());
                System.out.println(coloana.toString());
*/

                List<BaseProduct> ordered = deliveryService.createOrder(linie, clientFrame.getTable());

                int totalPrice = 0;

                for (BaseProduct b : ordered) {
                    totalPrice += b.computePrice();
                }

                System.out.println(ordered.toString());

                String insertDate = JOptionPane.showInputDialog(null, "Introduceti data comenzii de tipul : dd-Mmm-yyyy hh:mm:ss ", JOptionPane.OK_OPTION);
                Date date = null;
                Order order = null;
                try {
                    date = new Date(insertDate);

                    order = new Order(deliveryService.getComenzi().size() + 1, date, ordered.size(), totalPrice);

                    List<MenuItem> menuItems = new ArrayList<>();

                    menuItems.addAll(ordered);

                    deliveryService.getComenzi().put(order, menuItems);

                    deliveryService.getClientMap().put(order, clientCurentConectat);

                    Object[][] objects;

                    List<Integer> returnedList = new ArrayList<>();

                    objects = new Object[deliveryService.getComenzi().size()][4];

                    List<Order> order1 = (List<Order>) deliveryService.getComenzi().keySet().stream().toList();

                    int poz = 0;

                    for (Order o : order1) {
                        objects[poz][0] = o.getOrderID();
                        objects[poz][1] = o.getOrderDate();
                        objects[poz][2] = o.getCantitate();
                        objects[poz][3] = o.getPret();

                        ++poz;
                    }


                    employeeFrame.getTable().setModel(new DefaultTableModel(
                            objects,
                            new String[]{"Id", "Data Plasarii Comenzii", "Cantitate", "Pret"
                            }
                    ));


                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Format corect : dd-Mmm-yyyy hh:mm:ss ");
                }

                /*31-Dec-1998 23:37:50*/
                /*  Order order = new Order(date);
                 */

                try {
                    FileWriter bill = new FileWriter("Bill.txt");

                    bill.write("Factura : \n");

                    bill.write("Informatiile comenzii sunt urmatoarele:\n");

                    int an = order.getOrderDate().getYear() + 1900;

                    bill.write("Data plasarii comenzii : " + order.getOrderDate().getDay() + " - " + order.getOrderDate().getMonth() + " - " + an + "\n");
                    bill.write("Produse achizitionate: \n");

                    for (BaseProduct b : ordered) {
                        bill.write(b.toString() + "\n");
                    }

                    bill.write("Numarul produselor achizitionate: " + ordered.size() + "\n");

                    bill.write(" Totalul de plata :" + totalPrice + "\n");

                    System.out.println(" Factura a fost generata cu succes ");

                    bill.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NullPointerException ex) {

                    JOptionPane.showMessageDialog(null, "Nu s-a putut genera factura din cauza datei invalide");
                }


                linie.removeAll(linie);
                coloana.removeAll(coloana);
            }
        });


        clientFrame.getButonVizualizareProduse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[][] objects = new Object[deliveryService.getBaseProductList().size()][7];

                BaseProduct[] baseProducts = new BaseProduct[deliveryService.getBaseProductList().size()];

                baseProducts = deliveryService.getBaseProductList().toArray(new BaseProduct[0]);


                for (int i = 0; i < deliveryService.getBaseProductList().size(); i++) {
                    objects[i][0] = baseProducts[i].getTitle();
                    objects[i][1] = baseProducts[i].getRating();
                    objects[i][2] = baseProducts[i].getCalories();
                    objects[i][3] = baseProducts[i].getProtein();
                    objects[i][4] = baseProducts[i].getFat();
                    objects[i][5] = baseProducts[i].getSodium();
                    objects[i][6] = baseProducts[i].getPrice();
                }

                clientFrame.getTable().setModel(new DefaultTableModel(
                        objects,
                        new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                        }
                ));

            }
        });


        administratorFrame.getButonGenerateTimeReport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel panel = new JPanel(new GridLayout(4, 1));

                JTextField text1 = new JTextField(10);
                JTextField text2 = new JTextField(10);

                JLabel label1 = new JLabel("Ora de start:");
                JLabel label2 = new JLabel("Ora de final:");

                panel.add(label1);
                panel.add(text1);
                panel.add(label2);
                panel.add(text2);

                JOptionPane.showMessageDialog(null, panel);


                try {
                    int oraDeStart = Integer.parseInt(text1.getText());
                    int oraDeFinal = Integer.parseInt(text2.getText());

                    /* System.out.println("Ora de start "+ oraDeStart+" Ora de final : "+oraDeFinal);*/

                    FileWriter myWriter = new FileWriter("TimeReport.txt");

                    myWriter.write("Conform datelor introduse au rezultat urmatoarele comenzi:\n");

                    List<Order> orderList = deliveryService.getComenzi().keySet().stream().toList();

                    int cnt = 1;

                    for (Order o : orderList) {

                        if (o.getOrderDate().getHours() >= oraDeStart && o.getOrderDate().getHours() <= oraDeFinal) {
                            myWriter.write(cnt + ". ");
                            myWriter.write("ID_Comanda: " + o.getOrderID() + " Data comenzii: " + o.getOrderDate() + " Cantitatea : " + o.getCantitate() + " Total de plata:" + o.getPret() + "\n");

                        }
                        ++cnt;
                    }

                    /*myWriter.write("Files in Java might be tricky, but it is fun enough!");*/


                    myWriter.close();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input invalid pentru ora de start sau ora de final");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "A aparut o eroare la deschiderea fisierului");
                }


            }
        });

        administratorFrame.getButonGenerateNumberOfProducts().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String aparitiiCautate = JOptionPane.showInputDialog(null, "Introduceti numarul de aparitii cautat");
                int aparitii;
                try {

                    aparitii = Integer.parseInt(aparitiiCautate);

                    if (aparitii < 0) {
                        JOptionPane.showMessageDialog(null, "Valoarea pentru aparitii este invalida");

                    } else {


                        List<Order> keys = deliveryService.getComenzi().keySet().stream().toList();

                        List<BaseProduct> produseAchizitionate = new ArrayList<>();
                        List<Integer> aparitiiPerProdus = new ArrayList<>();

                        for (Order o : keys) {
                            List<MenuItem> menuItem = deliveryService.getComenzi().get(o);

                            for (MenuItem menuItems : menuItem) {
                                BaseProduct baseProduct = (BaseProduct) menuItems;

                                if (produseAchizitionate.contains(baseProduct) == false) {
                                    produseAchizitionate.add(baseProduct);
                                    aparitiiPerProdus.add(1);
                                } else {
                                    int indice = produseAchizitionate.indexOf(baseProduct);
                                    int valoareAnterioara = aparitiiPerProdus.get(indice).intValue();
                                    aparitiiPerProdus.set(indice, valoareAnterioara + 1);
                                }

                            }

                        }

                        FileWriter myWriter = new FileWriter("NumberOfTimes.txt");

                        myWriter.write("In urma verificarii a rezultat: \n");


                        for (Integer i : aparitiiPerProdus) {

                            if (i.intValue() >= aparitii) {

                                myWriter.write(produseAchizitionate.get(i).toString() + "  numarul de aparitii asociat este : " + i + "\n");

                            }
                        }

                        myWriter.close();


                    }
                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(null, "Numarul introdus este invalid");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

        administratorFrame.getButonGenerateClientsReport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel panel = new JPanel(new GridLayout(4, 1));

                JTextField text1 = new JTextField(10);
                JTextField text2 = new JTextField(10);

                JLabel label1 = new JLabel("Numarul de comenzi plasate de clienti");
                JLabel label2 = new JLabel("Valoarea totala a comenzii");

                panel.add(label1);
                panel.add(text1);
                panel.add(label2);
                panel.add(text2);

                JOptionPane.showMessageDialog(null, panel);

                List<Order> orderList = deliveryService.getComenzi().keySet().stream().toList();

                List<Client> clientList = new ArrayList<>();

                List<Integer> numarDeComenziPlasate = new ArrayList<>();

                try {
                    int numarComenzi = Integer.parseInt(text1.getText());
                    int pretComanda = Integer.parseInt(text2.getText());

                    for (Order o : orderList) {
                        System.out.println("Comanda :" + deliveryService.getComenzi().get(o) + " total : " + o.getPret() + "  Client: " + deliveryService.getClientMap().get(o).toString());

                        Client c = deliveryService.getClientMap().get(o);

                        if (clientList.contains(c) == false) {
                            clientList.add(c);
                            if (o.getPret() >= pretComanda)
                                numarDeComenziPlasate.add(1);
                            else
                                numarDeComenziPlasate.add(0);
                        } else {

                            int index = clientList.indexOf(c);

                            if (o.getPret() >= pretComanda) {
                                int valoareAnterioara = numarDeComenziPlasate.get(index);
                                numarDeComenziPlasate.set(index, valoareAnterioara + 1);
                            }

                        }

                    }
                    FileWriter myWriter = new FileWriter("Number_of_times_and_Price_Report.txt");

                    myWriter.write("In urma verificarii a rezultat: \n");


                    for (Integer i : numarDeComenziPlasate) {

                        if (i >= numarComenzi) {

                            myWriter.write(clientList.get(i).toString() + " " + i.intValue());


                        }

                    }
                    myWriter.close();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Input invalid pentru aceasta operatiune!");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        administratorFrame.getBtnGenerateproductsreport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dateString = JOptionPane.showInputDialog(null, "Insert the date");

                // System.out.println(dateString);

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                List<BaseProduct> productList = new ArrayList<>();
                List<Integer> aparitii = new ArrayList<>();

                Date date = null;
                try {

                    date = formatter.parse(dateString);

                    List<Order> orderList = deliveryService.getComenzi().keySet().stream().toList();

                    for (Order o : orderList) {
                        List<MenuItem> menuItem = deliveryService.getComenzi().get(o);

                        Date currentOrderDate = o.getOrderDate();
                        for (MenuItem menuItems : menuItem) {

                            BaseProduct baseProduct = (BaseProduct) menuItems;
                        }
                    }

                    int anIntrodus = date.getYear() + 1900;
                    int lunaIntrodusa = date.getMonth();

                    if (lunaIntrodusa == 0)
                        lunaIntrodusa++;

                    int ziuaIntrodusa = date.getDay();

                    System.out.println(anIntrodus + " " + lunaIntrodusa + " " + ziuaIntrodusa);

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Nu s-a putut crea o data valida cu datele introduse");
                }


            }

        });

        administratorFrame.getButonModifyProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(linie.toString());
                System.out.println(coloana.toString());

                String input = JOptionPane.showInputDialog(null, "Intoduceti noua valoare:");

                int lin = (Integer.parseInt(String.valueOf(linie.get(0))));
                int col = (Integer.parseInt(String.valueOf(coloana.get(0))));

                if (col == 0) {
                    String numeProdus = input;

                    administratorFrame.getTable().setValueAt(numeProdus, lin, col);

                } else if (col == 1) {
                    Double ratingProdus = null;
                    try {
                        ratingProdus = (Double.parseDouble(input));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Input invalid pentru coloana Rating");

                    }
                    administratorFrame.getTable().setValueAt(ratingProdus, lin, col);

                } else {
                    try {

                        int val = Integer.parseInt(input);
                        administratorFrame.getTable().setValueAt(val, lin, col);

                    } catch (NumberFormatException ex) {
                        String str = null;

                        if (col == 2)
                            str = "Calories";
                        else if (col == 3)
                            str = "Proteins";
                        else if (col == 4)
                            str = "Fat";
                        else if (col == 5)
                            str = "Sodium";
                        else if (col == 6)
                            str = "Price";


                        JOptionPane.showMessageDialog(null, "Valoare invalida pentru " + str);
                    }

                }


                linie.removeAll(linie);
                coloana.removeAll(coloana);

            }
        });

        administratorFrame.getButonDeleteProduct().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(linie.toString());
                System.out.println(coloana.toString());

                int lin = (Integer.parseInt(String.valueOf(linie.get(0))));
                int col = (Integer.parseInt(String.valueOf(coloana.get(0))));

                for (int i = lin; i < administratorFrame.getTable().getRowCount() - 1; i++) {
                    ArrayList<String> stringArrayList = new ArrayList<>();

                    for (int j = 0; j < 7; j++)
                        stringArrayList.add(administratorFrame.getTable().getValueAt(i + 1, j).toString());

                    for (int j = 0; j < 7; j++)
                        administratorFrame.getTable().setValueAt(stringArrayList.get(j), i, j);

                }

                linie.removeAll(linie);
                coloana.removeAll(coloana);

            }
        });


    }

    private void citireDate() {

        final String[] rezultateCitite = {""};

        List<BaseProduct> baseProductList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            baseProductList = (List<BaseProduct>) Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                return new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                            }

                    ).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] objects = new Object[baseProductList.size()][7];

        BaseProduct[] baseProducts = new BaseProduct[baseProductList.size()];

        baseProducts = baseProductList.toArray(new BaseProduct[0]);


        for (int i = 0; i < baseProductList.size(); i++) {
            objects[i][0] = baseProducts[i].getTitle();
            objects[i][1] = baseProducts[i].getRating();
            objects[i][2] = baseProducts[i].getCalories();
            objects[i][3] = baseProducts[i].getProtein();
            objects[i][4] = baseProducts[i].getFat();
            objects[i][5] = baseProducts[i].getSodium();
            objects[i][6] = baseProducts[i].getPrice();
        }

        administratorFrame.getTable().setModel(new DefaultTableModel(
                objects,
                new String[]{"Title", "Rating", "Calories", "Proteins", "Fat", "Sodium", "Price"
                }
        ));


    }


}
/*
    ArrayList<String> returnedComponents = ClientDAO.clientProperties(clientList.get(0));

    Object[][] objects = new Object[clientList.size()][5];

                for(int i=0;i<clientList.size();i++)
        {
        objects[i][0]=clientList.get(i).getId();
        objects[i][1]=clientList.get(i).getName();
        objects[i][2]=clientList.get(i).getAdress();
        objects[i][3]=clientList.get(i).getEmail();
        objects[i][4]=clientList.get(i).getAge();


        }

        //    view.getFirstWindow().getTable().setCol

        view.getFirstWindow().getTable().setModel( new DefaultTableModel(
        objects,
        returnedComponents.toArray()
        ));

        }
        });
*/
