package com.example.customerinterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapter.CategoryAdapter;
import Adapter.CategoryAdapter2;
import Adapter.RandomAdapter;
import Adapter.SearchAdapter;
import Domain.CategoryDomain;
import Domain.CategoryDomain2;
import Model.Products;

public class MainActivityCustomer extends AppCompatActivity {

    private ImageButton cusprofile;
    private ImageButton cuscart;
    private ImageButton cusorder;
    private ImageButton cusmanual;
    private EditText SearchPro;

    private ConstraintLayout CL_Order;

    private RandomAdapter adapter2;
    private CategoryAdapter adapter;
    private CategoryAdapter2 adapter1;
    private SearchAdapter adapter3;
    public RecyclerView recyclerViewCategoryList, recyclerViewPopularList, recyclerViewSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar
        setContentView(R.layout.activity_main_customer);

        cusprofile = (ImageButton) findViewById(R.id.CusProfileBtn);
        cusprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerProfileInterface();
            }
        });

        cuscart = (ImageButton) findViewById(R.id.card_btn);
        cuscart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerCartInterface();
            }
        });

        cusorder = (ImageButton) findViewById(R.id.CusMyOrderBtn);
        cusorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerMyOrderInterface();
            }
        });

        cusmanual = (ImageButton) findViewById(R.id.CusManualBtn);
        cusmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManualInterface();
            }
        });

//        SearchPro = findViewById(R.id.ShopInterfaceSearchBar2);
//        SearchPro.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                String searchText = SearchPro.getText().toString();
//                DisPro(searchText);
//                return true;
//            }
//        });

        CL_Order = (ConstraintLayout) findViewById(R.id.constraintLayoutOrder);
        CL_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerMyOrderInterface();
            }
        });

        recyclerViewCategory();
        recyclerViewCategory2();
        recyclerViewPopular();
    }

    private void DisPro(String str) {
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Products")//.child(String.valueOf("By" == GlobalOnline.username))
                        .orderByChild("ProductName")
                        .startAt(str)
                        .endAt(str + "-"),Products.class)
                .build();

        adapter3 = new SearchAdapter(options);
        adapter3.startListening();
        recyclerViewSearchList.setAdapter(adapter3);

        closekey();
    }

    private void closekey() {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(SearchPro.getWindowToken(), 0);
        }
    }

    public void openCustomerProfileInterface(){
        Intent intent = new Intent(this, UserProfileInterface.class);
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


    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<Products> foodlist = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Products").limitToLast(3),Products.class).build();
//        Collections.shuffle(foodlist);

        adapter2 = new RandomAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Stationary", "stationary"));
        categoryList.add(new CategoryDomain("Rice", "rice"));
        categoryList.add(new CategoryDomain("Beverage", "beverage"));
        categoryList.add(new CategoryDomain("Snacks", "snack"));
        categoryList.add(new CategoryDomain("Spices", "spices"));

        adapter = new CategoryAdapter(categoryList,this);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewCategory2() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView3);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain2> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain2("Bread", "bread"));
        categoryList.add(new CategoryDomain2("Medicine", "medicine"));
        categoryList.add(new CategoryDomain2("Laundry", "laundry"));
        categoryList.add(new CategoryDomain2("Toiletries", "toiletries"));
        categoryList.add(new CategoryDomain2("Kitchenware", "kitchenware"));
        categoryList.add(new CategoryDomain2("Animal Food", "animalfood"));


        adapter1 = new CategoryAdapter2(categoryList, this);
        recyclerViewCategoryList.setAdapter(adapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter2.stopListening();
    }
}