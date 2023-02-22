package BusinessLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeProduct extends MenuItem{

    private String name;
    private List<BaseProduct> baseProductList;

    public CompositeProduct (String name,List<BaseProduct> baseProductList)
    {
        this.name = name;
        this.baseProductList = baseProductList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int computePrice() {

     int sum=0;

        for(BaseProduct b:baseProductList)
        {
          sum+=b.computePrice();
        }
      return sum;
    }

    public void addBaseProduct(BaseProduct baseProduct)
    {
       this.baseProductList.add(baseProduct);

    }

    public void removeBaseProduct(BaseProduct baseProduct)
    {
        this.baseProductList.remove(baseProduct);
    }

    public void setBaseProductList(List<BaseProduct> baseProductList) {
        this.baseProductList = baseProductList;
    }

    public List<BaseProduct> getBaseProductsList() {
        return baseProductList;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;

        List<String> strings = new ArrayList<>();

        for(BaseProduct baseProduct : baseProductList)
        {
            strings.add(baseProduct.getTitle());
        }

        for( String s : strings )
        {
            result = result * prime + s.hashCode();
        }

        return result;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "name='" + name + '\'' +
                ", baseProductList=" + baseProductList +
                '}';
    }
}
