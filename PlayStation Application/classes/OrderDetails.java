package assign3;

import java.text.*;
import java.io.*;

import java.util.Date;
public class OrderDetails
{
int OrderId;
String ProductName;
int CustId;
int ProductId;
int Price;
int Quantity;
int QuantityAvailable;
int Rebates;
int Discounts;
int WarrantyCost;
String Warranty;
int WarrantyPeriod;
String OrderStatus;
String ProductDesc;
String WarrantyPurchase;
int Total;

 public int getOrderId() {
        return OrderId;
    }


    public void setOrderId(int OrderId) {
       this.OrderId= OrderId;
    }

	 public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
       this.ProductId = ProductId;
    }

	 public int getCustId() {
        return CustId;
    }

    public void setCustId(int CustId) {
       this.CustId = CustId;
    }


    public void setProductName(String ProductName) {
       this.ProductName = ProductName;
    }

	public String getProductName() {
        return ProductName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
       this.Quantity = Quantity;
    }


    public int getQuantityAvailable() {
        return  QuantityAvailable;
    }


    public void setQuantityAvailable(int QuantityAvailable) {
       this.QuantityAvailable = QuantityAvailable;
    }

    public int getRebates() {
        return Rebates;
    }


    public void setRebates(int Rebates) {
       this.Rebates = Rebates;
    }

    public int getDiscounts() {
        return Discounts;
    }


    public void setDiscounts(int Discounts) {
       this.Discounts = Discounts;
    }

    public int getPrice() {
        return Price;
    }


    public void setPrice(int Price) {
       this.Price = Price;
    }

    public String getWarranty() {
        return Warranty;
    }


    public void setWarranty(String Warranty) {
       this.Warranty=Warranty;
    }

    public int getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(int WarrantyPeriod) {
       this.WarrantyPeriod =WarrantyPeriod;
    }

    public int getWarrantyCost() {
        return WarrantyCost;
    }

    public void setWarrantyCost(int WarrantyCost) {
       this.WarrantyCost =WarrantyCost;
    }


    public void setWarrantyPurchase(String WarrantyPurchase) {
       this.WarrantyPurchase=WarrantyPurchase;
    }

    public String getWarrantyPurchase() {
        return WarrantyPurchase;
    }


     public void setOrderStatus(String OrderStatus) {
       this.OrderStatus=OrderStatus;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

     public void setTotal(int Total) {
       this.Total=Total;
    }

    public int getTotal() {
        return Total;
    }

}
