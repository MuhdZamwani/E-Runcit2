package Model;

import java.io.Serializable;

public class Products implements Serializable {

    private String ProductName, ProductPrice, ProductDesc,Category, ImageUrl, By, ShopName, ShopAdd, OwnerPhoneNo;
    private int numberInCart;
    private double TotalPrice;

    public Products(){
        //Empty
    }

    public Products( String ProductName, String ProductPrice, double TotalPrice,
                     String ProductDesc,String Category,
                     String ImageUrl, String By, int numberInCart, String ShopName, String ShopAdd,
                     String OwnerPhoneNo){

        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.TotalPrice = TotalPrice;
        this.ProductDesc = ProductDesc;
        this.Category = Category;
        this.ImageUrl = ImageUrl;
        this.By = By;
        this.ShopName = ShopName;
        this.ShopAdd = ShopAdd;
        this.OwnerPhoneNo = OwnerPhoneNo;
        this.numberInCart = numberInCart;
    }


    public String getProductName(){ return ProductName;}

    public void setProductName(String ProductName){ this.ProductName = ProductName;}

    public String getProductPrice(){ return ProductPrice;}

    public void setProductPrice(String ProductPrice){ this.ProductPrice = ProductPrice;}

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getProductDesc(){ return ProductDesc;}

    public void setProductDesc(String ProductDesc){ this.ProductDesc = ProductDesc;}

    public String getCategory(){ return Category;}

    public void setCategory(String Category){ this.Category = Category;}

    public String getImageUrl(){ return ImageUrl;}

    public void setImageUrl(String ImageUrl){ this.ImageUrl = ImageUrl;}

    public String getBy() { return By;}

    public void setBy(String by) {By = by;}

    public int getNumberInCart() { return numberInCart; }

    public void setNumberInCart(int numberInCart) { this.numberInCart = numberInCart; }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getShopAdd() {
        return ShopAdd;
    }

    public void setShopAdd(String ShopAdd) {
        this.ShopAdd = ShopAdd;
    }

    public String getOwnerPhoneNo() {
        return OwnerPhoneNo;
    }

    public void setOwnerPhoneNo(String OwnerPhoneNo) {
        this.OwnerPhoneNo = OwnerPhoneNo;
    }
}
