package Model;

public class Cart {
    private String CusUserName, ProductName, ProductPrice, ImageUrl, Category, FullName, PhoneNo, By, UID, ShopName, ShopPhoneNo, ShopAdd;
    private int Quantity;
    private double TotalPrice;

    public Cart() {
        //Empty
    }

    public Cart(String productName, String productPrice, double TotalPrice,  String imageUrl, String category,
                int quantity, String fullName, String phoneNo, String by, String CusUserName, String UID,
    String ShopName, String ShopPhoneNo, String ShopAdd) {
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.TotalPrice = TotalPrice;
        this.ImageUrl = imageUrl;
        this.Category = category;
        this.Quantity = quantity;
        this.FullName = fullName;
        this.PhoneNo = phoneNo;
        this.By = by;
        this.CusUserName = CusUserName;
        this.UID = UID;
        this.ShopName = ShopName;
        this.ShopPhoneNo = ShopPhoneNo;
        this.ShopAdd = ShopAdd;

    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getBy() {
        return By;
    }

    public void setBy(String by) {
        By = by;
    }

    public String getCusUserName() {
        return CusUserName;
    }

    public void setCusUserName(String cusUserName) {
        CusUserName = cusUserName;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopPhoneNo() {
        return ShopPhoneNo;
    }

    public void setShopPhoneNo(String shopPhoneNo) {
        ShopPhoneNo = shopPhoneNo;
    }

    public String getShopAdd() {
        return ShopAdd;
    }

    public void setShopAdd(String shopAdd) {
        ShopAdd = shopAdd;
    }
}
