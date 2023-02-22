package BusinessLayer;

import WritingIntoCSV.AppendFile;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.io.Serializable;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing,Serializable  {

    //final List<MenuItem> ... list of baseProducts or
    // siteProducts;

    private HashMap<Order, List<MenuItem>> comenzi;

    private HashSet<BaseProduct> baseProductList;

    private HashSet<CompositeProduct> compositeProductList;

    private HashMap<Order , Client> clientMap;

    public DeliveryService() {

        this.baseProductList = new HashSet<>();
        this.compositeProductList = new HashSet<>();
        this.clientMap=new HashMap<>();
        this.comenzi = new HashMap<>();



    }

    public HashMap<Order, List<MenuItem>> getComenzi() {
        return comenzi;
    }

    public void setComenzi(HashMap<Order, List<MenuItem>> comenzi) {
        this.comenzi = comenzi;
    }

    public HashSet<BaseProduct> getBaseProductList() {
        return baseProductList;
    }

    public void setBaseProductList(HashSet<BaseProduct> baseProductList) {
        this.baseProductList = baseProductList;
    }

    public HashSet<CompositeProduct> getCompositeProductList() {
        return compositeProductList;
    }

    public void setCompositeProductList(HashSet<CompositeProduct> compositeProductList) {
        this.compositeProductList = compositeProductList;
    }



    public HashMap<Order, Client> getClientMap() {
        return clientMap;
    }

    public void setClientMap(HashMap<Order, Client> clientMap) {
        this.clientMap = clientMap;
    }

    @Override
    public String importProducts() {

        final String[] outputString = {""};

        Path path = Path.of("products.csv");

        try {

            baseProductList = (HashSet<BaseProduct>) Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                outputString[0] = outputString[0] + fields[0] + fields[1] + fields[2] + fields[3] + fields[4] + fields[5] + fields[6];
                                outputString[0] = outputString[0] + "\n";

                                return new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));

                            }

                    ).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputString[0];


    }

    @Override
    public void addProduct(BaseProduct baseProduct) {

        //preconditie

        assert baseProduct.getTitle().isEmpty() || baseProduct.getPrice()<0 || baseProduct.getProtein() < 0||
                baseProduct.getFat()<0 || baseProduct.getSodium() <0 || baseProduct.getCalories() <0 ||
                baseProduct.getRating() <0;

        int lastSize = baseProductList.size();

        baseProductList.add(baseProduct);

        //postConditie
        assert  baseProductList.size() != lastSize + 1;

        //invariant: nu se schimba = metoda -> IDelivery Service

        isWellFormed();




    }

    @Override
    public void deleteProduct(List<Integer> linie) {

        assert linie.size() == 0;

        BaseProduct[] baseProducts = baseProductList.toArray(new BaseProduct[0]);

        List<String[]> insert = new ArrayList<>();

        assert  baseProductList.size() ==0;

        int lastSize = baseProductList.size();

        if(baseProductList.isEmpty()==false) {

            for (Integer it : linie) {
                baseProductList.remove(baseProducts[it]);
            }
        }

        assert (baseProductList.size()!=lastSize-1);

        isWellFormed();

    }


    @Override
    public List<BaseProduct> createOrder(List<Integer> linie, JTable table) {

        String[] result = new String[7];

        List<BaseProduct> ordered = new ArrayList<>();


        for (int i = 0; i < linie.size(); i++) {

            BaseProduct aux;

            for (int j = 0; j < 7; j++)
                result[j] = String.valueOf(table.getModel().getValueAt(linie.get(i), j));

            aux = new BaseProduct(result[0], Double.parseDouble(result[1]), Integer.parseInt(result[2]),
                    Integer.parseInt(result[3]), Integer.parseInt(result[4]), Integer.parseInt(result[5]),
                    Integer.parseInt(result[6]));

            ordered.add(aux);


            ordered.get(i).setPrice(aux.computePrice());

        }

        setChanged();
        notifyObservers();

        return ordered;


    }


    @Override
    public void createCompositePorduct(String name, List<Integer> linie) {

        BaseProduct[] baseProducts = baseProductList.toArray(new BaseProduct[0]);

        List<BaseProduct> base = new ArrayList<>();

        double commonRating = 0.0;
        int clories = 0, proteins = 0, price = 0, sodium = 0, fat = 0;

        for (Integer b : linie) {
            base.add(baseProducts[b]);
            commonRating = commonRating + baseProducts[b].getRating();
            clories = clories + baseProducts[b].getCalories();
            proteins = proteins + baseProducts[b].getProtein();
            price = price + baseProducts[b].getPrice();
            sodium = sodium + baseProducts[b].getSodium();
            fat = fat + baseProducts[b].getFat();
        }

        CompositeProduct compositeProduct = new CompositeProduct(name, base);

        compositeProductList.add(compositeProduct);

        /*  System.out.println(compositeProduct.toString());*/

        List<String[]> dataLines = new ArrayList<>();

       /* dataLines.add(new String[]{name,String.valueOf(rating),String.valueOf(calories),String.valueOf(protein),String.valueOf(fat),String.valueOf(sodium),String.valueOf(price)});

        AppendFile appendFile = new AppendFile();

        appendFile.givenDataArray_whenConvertToCSV_thenOutputCreated_Append(dataLines);*/

        dataLines.add(new String[]{name, String.valueOf(commonRating), String.valueOf(clories), String.valueOf(proteins), String.valueOf(fat), String.valueOf(sodium), String.valueOf(price)});

        AppendFile appendFile = new AppendFile();

        try {
            appendFile.givenDataArray_whenConvertToCSV_thenOutputCreated_Append(dataLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BaseProduct> findProductsWhichContains(String string) {

        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getTitle().contains(string)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return returnedList;

    }


    @Override
    public List<BaseProduct> findProductWithRating(double rating) {

        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getRating() == rating).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return returnedList;
    }

    @Override
    public List<BaseProduct> findProductWithNumberOfCalories(int numberOfCalories) {

        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getCalories() == numberOfCalories).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return returnedList;
    }

    @Override
    public List<BaseProduct> findProductWithNumberOfProteins(int numberOfProteins) {

        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getProtein() == numberOfProteins).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return returnedList;
    }

    @Override
    public List<BaseProduct> findProductWithNumberOfFats(int numberOfFats) {

        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getFat() == numberOfFats).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnedList;

    }

    @Override
    public List<BaseProduct> findProductWithNumberOfSodium(int numberOfSodium) {
        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getSodium() == numberOfSodium).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnedList;
    }

    @Override
    public List<BaseProduct> findProductWithPrice(int price) {
        List<BaseProduct> returnedList = new ArrayList<>();

        Path path = Path.of("products.csv");

        try {

            returnedList = Files.lines(path)
                    .skip(1)
                    .map(line -> {

                                String[] fields = line.split(",");
                                BaseProduct baseProduct = null;
                                baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                                return baseProduct;
                            }

                    ).filter(baseProduct -> baseProduct.getPrice() == price).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnedList;
    }

    @Override
    public List<BaseProduct> findProductWithMultipleCriteria(List<String> criteria, List<String> items) {

        List<BaseProduct> baseProductSearched = new ArrayList<>();

        for(BaseProduct b:baseProductList)
        {
            boolean ok = true;

            for(int i=0;i<criteria.size();i++)
            {
                if(criteria.get(i).equals("Search by Rating"))
                {
                    try{
                          double doubleRating = Double.parseDouble(items.get(i));

                          if(b.getRating() != doubleRating)
                          {
                              ok=false;
                              break;
                          }

                       }catch(NumberFormatException exception)
                    {
                        JOptionPane.showMessageDialog(null,"Rating-ul introdus este invalid");

                    }

                }else
                if(criteria.get(i).equals("Search by Calories"))
                {
                    try{
                        int integerCalories = Integer.parseInt(items.get(i));

                        if(b.getCalories() != integerCalories)
                        {
                            ok=false;
                            break;
                        }

                    }catch(NumberFormatException exception)
                    {
                        JOptionPane.showMessageDialog(null,"Caloriile-ul introduse sunt invalide");

                    }

                }else
                if(criteria.get(i).equals("Search by Proteins"))
                {
                    try{
                        int integerProteins = Integer.parseInt(items.get(i));

                        if(b.getProtein() != integerProteins)
                        {
                            ok=false;
                            break;
                        }

                    }catch(NumberFormatException exception)
                    {
                        JOptionPane.showMessageDialog(null,"Proteinele introduse sunt invalide");

                    }

                } else
                if(criteria.get(i).equals("Search by Fat"))
                {
                    try{
                        int integerFat = Integer.parseInt(items.get(i));

                        if(b.getFat() != integerFat)
                        {
                            ok=false;
                            break;
                        }

                    }catch(NumberFormatException exception)
                    {
                        JOptionPane.showMessageDialog(null,"Fat-ul introdus este invalid");

                    }

                }

                else
                if(criteria.get(i).equals("Search by Sodium"))
                {
                    try{
                        int integerSodium = Integer.parseInt(items.get(i));

                        if(b.getSodium() != integerSodium)
                        {
                            ok=false;
                            break;
                        }

                    }catch(NumberFormatException exception)
                    {
                        JOptionPane.showMessageDialog(null,"Sodium-ul introdus este invalid");

                    }

                }

                else
                if(criteria.get(i).equals("Search by Price"))
                {
                    try{
                        int integerPrice = Integer.parseInt(items.get(i));

                        if(b.getPrice() != integerPrice)
                        {
                            ok=false;
                            break;
                        }

                    }catch(NumberFormatException exception)
                    {
                        JOptionPane.showMessageDialog(null,"Price-ul introdus este invalid");

                    }

                }else
              if(criteria.get(i).equals("Search by KeyWord"))
              {
                  String cuvant  = items.get(i);

                  if(b.getTitle().contains(cuvant)==false)
                  {
                      ok=false;
                      break;
                  }

              }



            }

            if(ok==true)
            {

                baseProductSearched.add(b);
            }




        }


        System.out.println(baseProductSearched.toString());

        return baseProductSearched;

    }

    //comnezi nenul baseprList.size > 0

    @Override
    public boolean isWellFormed() {

       // if(comenzi.isEmpty() baseProductList.size() <=0)

        List<Order> orderList = (List<Order>) comenzi.keySet().stream().toList();

        boolean ok =true;

        for(Order o:orderList)
        {
           if(clientMap.get(o).getUserName().isEmpty()||clientMap.get(o).getPassWord().isEmpty())
               ok = false;

           if(o.getOrderID()<0)

               ok= false;

        }


        return ok;

    }



    @Override
    public void showProductsFromStock() {

    }



}
