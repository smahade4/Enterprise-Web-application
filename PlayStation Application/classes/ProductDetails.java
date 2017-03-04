import java.text.*;

import java.util.Date;

public class ProductDetails
{
int ProductId;
String Product;
String ProductName;
String RetailerName;
String ManufacturerName;
String ProductDescription;
String RetailerZip;
String RetailerCity;
String RetailerState;
int QuantityAvailable;
int Rebates;
int Price;
int Discounts;
String Warranty;
int WarrantyCost;
int WarrantyPeriod;
//Date ShippingDate;
 public int getProductId() {
        return ProductId;
    }


    public void setProductId(int ProductId) {
       this.ProductId = ProductId;
    }

	public String getProduct() {
        return Product;
    }


    public void setProduct(String Product) {
       this.Product = Product;
    }


    public String getProductName() {
        return ProductName;
    }


    public void setProductName(String ProductName) {
       this.ProductName = ProductName;
    }

    public String getRetailerName() {
        return RetailerName;
    }


    public void setRetailerName(String RetailerName) {
       this.RetailerName = RetailerName;
    }

    public String getRetailerCity() {
        return RetailerCity;
    }


    public void setRetailerCity(String RetailerCity) {
       this.RetailerCity = RetailerCity;
    }

    public String getRetailerState() {
        return RetailerState;
    }


    public void setRetailerState(String RetailerState) {
       this.RetailerState = RetailerState;
    }

    public String getRetailerZip() {
        return RetailerZip;
    }


    public void setRetailerZip(String RetailerZip) {
       this.RetailerZip = RetailerZip;
    }


    public String getManufacturerName() {
        return ManufacturerName;
    }


    public void setManufacturerName(String ManufacturerName) {
       this.ManufacturerName= ManufacturerName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }


    public void setProductDescription(String  ProductDescription) {
       this.ProductDescription = ProductDescription;
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
/*public Date getShippingDate()
{
return ShippingDate;
}

public void setShippingDate(Date ShippingDate)
{
this.ShippingDate=ShippingDate;
}*/

}