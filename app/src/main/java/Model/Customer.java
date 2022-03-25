package Model;

public class Customer{

    private String FullName, Username, Email, PhoneNo, Password;

    public Customer(){

    }

    public Customer(String FullName, String Username, String Email, String PhoneNo, String Password){
        this.FullName = FullName;
        this.Username = Username;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
        this.Password = Password;
    }

    public String getFullName(){
        return FullName;
    }
    public void setFullName(String FullName){
        this.FullName = FullName;
    }

    public String getUsername(){
        return Username;
    }
    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }

    public String getPhoneNo(){
        return PhoneNo;
    }
    public void setPhoneNo(String PhoneNo){
        this.PhoneNo = PhoneNo;
    }

    public String getPassword(){
        return Password;
    }
    public void setPassword(String Pass){ this.Password = Password;
    }
}
