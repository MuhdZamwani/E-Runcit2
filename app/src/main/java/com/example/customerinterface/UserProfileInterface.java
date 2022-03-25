package com.example.customerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.GlobalOnline;

public class UserProfileInterface extends AppCompatActivity {

    private ImageButton cushome;
    private ImageButton cuscart;
    private ImageButton cusorder;
    private ImageButton cusmanual;
//    private ImageView addressedit;
    private TextView fullName, userName, email, phoneNo, add;
    private DatabaseReference FullName, UserName, Email, PhoneNo, Add, seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_user_profile_interface);

        FullName = FirebaseDatabase.getInstance().getReference().child("Customer").child(GlobalOnline.currentOnline.getUsername()).child("Customer Details").child("FullName");

        fullName = findViewById(R.id.CusNameEdit);
        FullName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                fullName.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        UserName = FirebaseDatabase.getInstance().getReference().child("Customer").child(GlobalOnline.currentOnline.getUsername()).child("Customer Details").child("Username");

        userName = findViewById(R.id.CusUsernameEdit);
        UserName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                userName.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Email = FirebaseDatabase.getInstance().getReference().child("Customer").child(GlobalOnline.currentOnline.getUsername()).child("Customer Details").child("Email");

        email = findViewById(R.id.CusEmailEdit);
        Email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                email.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        PhoneNo = FirebaseDatabase.getInstance().getReference().child("Customer").child(GlobalOnline.currentOnline.getUsername()).child("Customer Details").child("PhoneNo");

        phoneNo = findViewById(R.id.CusPhoneNoEdit);
        PhoneNo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                phoneNo.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        cushome = (ImageButton) findViewById(R.id.CusHomeBtn1);
        cushome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerHomeInterface();
            }
        });

        cuscart = (ImageButton) findViewById(R.id.card_btn1);
        cuscart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerCartInterface();
            }
        });

        cusorder = (ImageButton) findViewById(R.id.CusMyOrderBtn1);
        cusorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerMyOrderInterface();
            }
        });

        cusmanual = (ImageButton) findViewById(R.id.CusManualBtn1);
        cusmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManualInterface();
            }
        });

//        addressedit = (ImageView) findViewById(R.id.AddMoreAddressbtn);
//        addressedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCustomerProfileAddressInterface();
//            }
//        });
    }

    public void openCustomerHomeInterface(){
        Intent intent = new Intent(this, MainActivityCustomer.class);
        startActivity(intent);
    }

    public void openCustomerCartInterface(){
        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
    }

    public void openCustomerMyOrderInterface(){
        Intent intent = new Intent(this, UserMyOrderInterface.class);
        startActivity(intent);
    }

    public void openCustomerManualInterface(){
        Intent intent = new Intent(this, UserManualInterface.class);
        startActivity(intent);
    }
//
//    public void openCustomerProfileAddressInterface() {
//        Intent intent = new Intent(this, UserAddressInterface.class);
//        startActivity(intent);
//    }
}