package BusinessLayer;

import javax.swing.*;
import java.util.List;

public interface IDeliveryServiceProcessing {

    String importProducts();

    void addProduct(BaseProduct baseProduct);

    void deleteProduct(List<Integer>linie);


    List<BaseProduct> createOrder(List<Integer> linie, JTable table);

    void createCompositePorduct(String name ,List<Integer> linie);

    List<BaseProduct> findProductsWhichContains(String string);


    List<BaseProduct> findProductWithRating(double rating);

    List<BaseProduct> findProductWithNumberOfCalories(int numberOfCalories);

    List<BaseProduct> findProductWithNumberOfProteins(int numberOfProteins);

    List<BaseProduct> findProductWithNumberOfFats(int numberOfFats);

    List<BaseProduct> findProductWithNumberOfSodium(int numberOfSodium);

    List<BaseProduct> findProductWithPrice(int price);

    List<BaseProduct> findProductWithMultipleCriteria(List<String> criteria,List<String>items);

    //returneaza un boolean

    //invariant = pentru fiecare instanta , fiecare conditie este adevarata pentru toate

    //is well formed

    //obiectul de tip client sa fie n

    boolean isWellFormed();

    void showProductsFromStock();

}
