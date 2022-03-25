package com.example.customerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserAddressInterface extends AppCompatActivity {

    private ImageButton cushome;
    private ImageButton cusprofile;
    private ImageButton cuscart;
    private ImageButton cusorder;
    private ImageButton cusmanual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_user_address_interface);

        cushome = (ImageButton) findViewById(R.id.CusHomeBtn5);
        cushome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerHomeInterface();
            }
        });

        cusprofile = (ImageButton) findViewById(R.id.CusProfileBtn5);
        cusprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerProfileInterface();
            }
        });

        cuscart = (ImageButton) findViewById(R.id.card_btn5);
        cuscart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerCartInterface();
            }
        });

        cusorder = (ImageButton) findViewById(R.id.CusMyOrderBtn5);
        cusorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerMyOrderInterface();
            }
        });

        cusmanual = (ImageButton) findViewById(R.id.CusManualBtn5);
        cusmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManualInterface();
            }
        });

    }

    public void openCustomerHomeInterface() {
        Intent intent = new Intent(this, MainActivityCustomer.class);
        startActivity(intent);
    }

    public void openCustomerProfileInterface(){
        Intent intent = new Intent(this, UserProfileInterface.class);
        startActivity(intent);
    }

    public void openCustomerCartInterface() {
        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
    }

    public void openCustomerMyOrderInterface() {
        Intent intent = new Intent(this, UserMyOrderInterface.class);
        startActivity(intent);
    }

    public void openCustomerManualInterface() {
        Intent intent = new Intent(this, UserManualInterface.class);
        startActivity(intent);
    }

}
