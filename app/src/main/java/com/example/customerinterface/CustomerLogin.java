package com.example.customerinterface;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Customer;
import Model.GlobalOnline;

public class CustomerLogin extends AppCompatActivity {

    private TextInputEditText InputLogUsername, InputLogPassword;
    private Button customersignup, logincustomer;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_customer_login);

        InputLogUsername = findViewById(R.id.CustUserNameLog);
        InputLogPassword = findViewById(R.id.CustPassLog);

        customersignup = (Button) findViewById(R.id.CustomerSignUp);
        customersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerSignUp();
            }
        });

        logincustomer = (Button) findViewById(R.id.CustomerLoginEnter);
        logincustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerLogin();

            }
        });
    }

    public void openCustomerSignUp(){
        Intent intent = new Intent(this, CustomerSignUp.class);
        startActivity(intent);
    }

    public void openCustomerLogin(){

        String username = InputLogUsername.getText().toString();
        String pass = InputLogPassword.getText().toString();

        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please Enter your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Please Enter your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LogAccessToAccount(username, pass);

        }
//        Intent intent = new Intent(this, MainActivityCustomer.class);
//        startActivity(intent);
    }

    private void LogAccessToAccount(final String username,
                                    final String pass){

        final DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Customer").child(username).exists()){
                    Customer customerData = snapshot.child("Customer").child(username).child("Customer Details").getValue(Customer.class);
                    if (customerData.getUsername().equals(username)){
                        if (customerData.getPassword().equals(pass)){
                            if("Customer".equals("Customer")){

                                Toast.makeText(CustomerLogin.this, "Logged Has Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomerLogin.this, MainActivityCustomer.class);
                                GlobalOnline.currentOnline = customerData;
                                progressDialog = new ProgressDialog(CustomerLogin.this);
                                progressDialog.show();
                                progressDialog.setContentView(R.layout.loading_animation);
                                progressDialog.setCancelable(true);
                                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(CustomerLogin.this,"Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(CustomerLogin.this, "Account with this " + username + " number do not exists.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}