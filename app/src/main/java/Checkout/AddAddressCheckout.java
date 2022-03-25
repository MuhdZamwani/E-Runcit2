package Checkout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.customerinterface.CartListActivity;
import com.example.customerinterface.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddAddressCheckout extends AppCompatActivity implements PaymentResultListener {
    EditText AddLine1, AddLine2, AddLine3;
    String Line1, Line2, Line3;

    private String sBy = "";
    private String sShopName = "";
    private String sShopPhoneNo = "";
    private String sShopAdd = "";
    private String sProPic = "";
    private String sProName = "";
    private String sQuantity = "";
    private String sCusName = "";
    private String sCusPhoneNo = "";
    private String sAmount = "";
    private String sEmail = "";

    String randomKey = UUID.randomUUID().toString();
    Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar
        setContentView(R.layout.activity_add_address_checkout);

        String sID = getIntent().getStringExtra("ProductID");
        sBy = getIntent().getStringExtra("ByOwner");
        sShopName = getIntent().getStringExtra("ShopName");
        sShopPhoneNo = getIntent().getStringExtra("ShopPhoneNo");
        sShopAdd = getIntent().getStringExtra("ShopAdd");
        sProPic = getIntent().getStringExtra("ProductPic");
        sProName = getIntent().getStringExtra("ProductName");
        sAmount = getIntent().getStringExtra("TotalPrice");
        sCusName = getIntent().getStringExtra("CusName");
        sCusPhoneNo = getIntent().getStringExtra("CusPhoneNo");
        sEmail = getIntent().getStringExtra("CusEmail");
//        sAmount = String.valueOf(sTotal);
//        String sAmount = String.valueOf(sTotal);


        AddLine1 = findViewById(R.id.ad_address);
        AddLine2 = findViewById(R.id.ad_code);
        AddLine3 = findViewById(R.id.ad_city);
        btPay = findViewById(R.id.ad_payment);


            double amount = Math.round(Double.parseDouble(sAmount) * 100);

            btPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Line1 = AddLine1.getText().toString();
                    Line2 = AddLine2.getText().toString();
                    Line3 = AddLine3.getText().toString();

                    if (TextUtils.isEmpty(Line1)) {
                        showError(AddLine1, "Please Enter Address");
                    } else if (TextUtils.isEmpty(Line2)) {
                        showError(AddLine2, "Please Enter Postcode");
                    } else if (TextUtils.isEmpty(Line3)) {
                        showError(AddLine3, "Please Enter City");
                    } else {

                        Checkout checkout = new Checkout();
                        //set key id
                        checkout.setKeyID("rzp_test_PMhoIO6emFpcof");
                        //set img
                        checkout.setImage(R.drawable.rzp_logo);
                        JSONObject object = new JSONObject();
                        try {
                            //put name
                            object.put("name", "Android Coding");
                            //put description
                            object.put("description", "Text Payment");
                            //put Theme Color
                            object.put("theme.color", "#0093DD");
                            //put currency unit
                            object.put("currency", "MYR");
                            //put amount
                            object.put("amount", amount);
                            //put mobile number
                            object.put("prefill.contact", sCusPhoneNo);
                            //put email
                            object.put("prefill.contract", sEmail);

                            //open razorPay checkout activity
                            checkout.open(AddAddressCheckout.this, object);

                            //add to order
                            Map<String, Object> OrderData = new HashMap<>();
                            OrderData.put("UID", sID);
                            OrderData.put("By", sBy);
                            OrderData.put("ShopName", sShopName);
                            OrderData.put("ShopPhoneNo", sShopPhoneNo);
                            OrderData.put("ShopAdd", sShopAdd);
                            OrderData.put("ImageUrl", sProPic);
                            OrderData.put("ProductName", sProName);
                            OrderData.put("TotalPrice", Double.valueOf(sAmount));
                            OrderData.put("Quantity", Double.valueOf(getIntent().getStringExtra("Quantity")));
                            OrderData.put("CusName", sCusName);
                            OrderData.put("CusPhoneNo", sCusPhoneNo);
                            OrderData.put("CusEmail", sEmail);
                            OrderData.put("CusAddress", AddLine1.getText().toString());
                            OrderData.put("CusPoscode", AddLine2.getText().toString());
                            OrderData.put("CusCity", AddLine3.getText().toString());
                            OrderData.put("Status", "Not Ready To Delivery Out");

                            FirebaseDatabase.getInstance().getReference().child("Order").child(randomKey).updateChildren(OrderData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Map<String, Object> IncomeData = new HashMap<>();
                                                IncomeData.put("By", sBy);
                                                IncomeData.put("Total", amount);

                                                FirebaseDatabase.getInstance().getReference().child("Total Income").child(randomKey).updateChildren(IncomeData)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                //Remove item from cart
                                                                FirebaseDatabase.getInstance().getReference().child("Cart List")
                                                                        .child(sID).removeValue();
                                                            }
                                                        });
                                            }
                                        }
                                    });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }});
        }


    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    public void openBackToCart(){
        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentSuccess(String s) {

        //initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set tittle
        builder.setTitle("Payment ID");
        //set message
        builder.setMessage(s);
        //show alert dialog
        builder.show();

        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int i, String s) {
        //Display Toast
        Toast.makeText(getApplicationContext(),
                s,Toast.LENGTH_SHORT).show();
    }
//    public void openAddAdressBtn(){
//        Intent intent = new Intent(this, MainActivityCustomer.class);
//        startActivity(intent);
//    }

}