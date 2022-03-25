package com.example.customerinterface;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.UUID;

import Model.CartID;
import Model.GlobalOnline;
import Model.Products;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCardBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private TextView sName, sLoc,sNum;
    private ImageView plusBtn, minusBtn, picFood;
    private Products pro;
    private int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_show_detail);

        initView();
        getBundle();
    }

    private void getBundle() {
        pro = (Products) getIntent().getSerializableExtra("object");


        Glide.with(ShowDetailActivity.this)
                .load(pro.getImageUrl())
                .into(picFood);

        titleTxt.setText(pro.getProductName());
        feeTxt.setText(pro.getProductPrice());
        descriptionTxt.setText(pro.getProductDesc());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        sName.setText(pro.getShopName());
        sLoc.setText(pro.getShopAdd());
        sNum.setText(pro.getOwnerPhoneNo());

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proname = titleTxt.getText().toString();
                String proprice = feeTxt.getText().toString();
                int quantity = Integer.parseInt(numberOrderTxt.getText().toString());

                addingToCartList(proname,proprice,quantity);
            }
        });
    }

    private void addingToCartList(final String proName,
                                  final String proPri,
                                  final int quantity) {
        final String randomKey = UUID.randomUUID().toString();
        final DatabaseReference cartListRef ;
        cartListRef= FirebaseDatabase.getInstance().getReference();

        cartListRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String full =  snapshot.child("Customer").child(GlobalOnline.currentOnline.getUsername()).child("Customer Details").child("FullName").getValue().toString();
                        String phoneNo = snapshot.child("Customer").child(GlobalOnline.currentOnline.getUsername()).child("Customer Details").child("PhoneNo").getValue().toString();
                        String ID = randomKey;
                        double newValue = Math.round(Double.parseDouble(proPri)) * (Double.valueOf(quantity));

                        HashMap<String, Object> cartData = new HashMap<>();
                        cartData.put("UID",ID);
                        cartData.put("CusUserName", GlobalOnline.currentOnline.getUsername());
                        cartData.put("FullName", full);
                        cartData.put("PhoneNo", phoneNo);
                        cartData.put("ProductName", proName);
                        cartData.put("ProductPrice", proPri);
                        cartData.put("Category",pro.getCategory());
                        cartData.put("ImageUrl",pro.getImageUrl());
                        cartData.put("Quantity", quantity);
                        cartData.put("TotalPrice",newValue);
                        cartData.put("ShopName",pro.getShopName());
                        cartData.put("ShopPhoneNo",pro.getOwnerPhoneNo());
                        cartData.put("ShopAdd",pro.getShopAdd());
                        cartData.put("By", pro.getBy());
                        cartListRef.child("Cart List").child(ID).updateChildren(cartData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(ShowDetailActivity.this, "Item successfully added to cart", Toast.LENGTH_SHORT).show();
                                CartID.cartID = ID;
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void initView() {
        addToCardBtn = findViewById(R.id.addToCardBtn);

        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);

        sName = findViewById(R.id.DisShop);
        sLoc = findViewById(R.id.DisLoc);
        sNum = findViewById(R.id.DisNum);

        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);

        picFood = findViewById(R.id.foodPic);
    }


}