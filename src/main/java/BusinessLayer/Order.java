package BusinessLayer;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {

    private int orderID;
    private Date orderDate;
    private int cantitate;
    private int pret;



    public Order( Date orderDate , int cantitate)
    {
        this.orderDate  = orderDate;
        this.orderID = 1;
        this.cantitate = cantitate;
        this.pret = 0;

    }


    public Order(int orderID, Date orderDate,int cantitate,int price)
    {
        this.orderDate  = orderDate;
        this.orderID = orderID;
        this.cantitate = cantitate;
        this.pret = price;

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        Order order = (Order) o;

        if(order.getOrderDate().equals(this.getOrderDate()) && order.getOrderID() == this.getOrderID()  )
              return true;
              else
              return false;


    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }
}
