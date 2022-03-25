package com.example.customerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import Adapter.CartViewAdapter;
import Adapter.CartViewHolder;
import Checkout.AddAddressCheckout;
import Model.Cart;
import Model.GlobalOnline;
import eventbus.MyUpdateCartEvent;

//import Adapter.CartActivityAdapter;
//import Helper.ManagementCart;

//import Helper.ManagementCart;

public class CartListActivity extends AppCompatActivity {

    private CartViewHolder adapter;
    private CartViewAdapter adapt;
    private RecyclerView recyclerViewList;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, checkoutbtn;
    private ScrollView scrollView;

    private ImageButton cushome;
    private ImageButton cusprofile;
    private ImageButton cusorder;
    private ImageButton cusmanual;

    private double oneTypeProductTPrice, OverPrice, test1;
//    private double OverPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar
        setContentView(R.layout.customer_activity_cart_list);

//        totalFeeTxt = findViewById(R.id.totalFeeTxt);
//        taxTxt = findViewById(R.id.taxTxt);
//        deliveryTxt = findViewById(R.id.deliveryTxt);
//        totalTxt = findViewById(R.id.totalTxt);
//        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);

        cushome = findViewById(R.id.CusHomeBtn2);
        cushome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerHomeInterface();
            }
        });

        cusprofile = findViewById(R.id.CusProfileBtn2);
        cusprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerProfileInterface();
            }
        });

        cusorder = findViewById(R.id.CusMyOrderBtn2);
        cusorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerMyOrderInterface();
            }
        });

        cusmanual = findViewById(R.id.CusManualBtn2);
        cusmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManualInterface();
            }
        });

//        checkoutbtn = findViewById(R.id.checkOutBtn);
//        checkoutbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCheckoutInterface();
//            }
//        });

        DisCart();

    }

    public void openCustomerHomeInterface(){
        Intent intent = new Intent(this, MainActivityCustomer.class);
        startActivity(intent);
    }

    public void openCustomerProfileInterface(){
        Intent intent = new Intent(this, UserProfileInterface.class);
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

    public void openCheckoutInterface(){
        Intent intent = new Intent(this, AddAddressCheckout.class);
        startActivity(intent);
    }

    private void DisCart() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList = findViewById(R.id.recyclerviewcard);
        recyclerViewList.setLayoutManager(linearLayoutManager);

//        int delFee = 10;
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Cart List")
                        .orderByChild("CusUserName")
                        .equalTo(GlobalOnline.currentOnline.getUsername())
                        ,Cart.class)
                .build();

//        deliveryTxt.setText(Integer.toString(delFee));

//        double newValue = getIntent().getDoubleExtra("OverPrice",0);
//        Toast.makeText(CartListActivity.this,"RM"+Double.toString(OverPrice), Toast.LENGTH_SHORT).show();
//        totalFeeTxt.setText("RM"+ Double.toString(OverPrice));
//        cartLoadListener.onCartLoadSuccess(cart);
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("MyOverPrice"));

        adapt = new CartViewAdapter(options);
        recyclerViewList.setAdapter(adapt);
        content();
    }

    private void content() {
        refresh(1000);
    }

    private void refresh(int milliseconds) {
            final Handler handler = new Handler ();
            final Runnable runnable = new Runnable () {
                @Override
                public void run() {
                    content();
                }
            };
            handler.postDelayed(runnable, milliseconds);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Cart List")
                                .orderByChild("CusUserName")
                                .equalTo(GlobalOnline.currentOnline.getUsername())
                        ,Cart.class)
                .build();
        FirebaseRecyclerAdapter<Cart,CartViewHolder>adapt = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options){
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart cart) {
                DecimalFormat dfZero = new DecimalFormat("0.00");

                Glide.with(holder.pic.getContext()).load(cart.getImageUrl()).into(holder.pic);
                holder.title.setText(cart.getProductName());
                holder.feeEachItem.setText(new StringBuilder().append(cart.getProductPrice()));
                holder.num.setText(new StringBuilder().append(cart.getQuantity()));
                holder.totalEachItem.setText(String.valueOf(cart.getTotalPrice()));

//                oneTypeProductTPrice = (cart.getQuantity()) * Double.parseDouble(cart.getProductPrice()); //(cart.getTotalPrice()) - 1 ;
//                OverPrice = oneTypeProductTPrice + OverPrice;
//                Intent intent = new Intent("MyOverPrice");
//                intent.putExtra("OverPrice", (OverPrice));
//                LocalBroadcastManager.getInstance(CartListActivity.this).sendBroadcast(intent);

                holder.minusItem.setOnClickListener(v -> {
                    cart.setQuantity(cart.getQuantity()-1);
                    if(cart.getQuantity() >= 1){
                        cart.setTotalPrice(cart.getQuantity() * Float.parseFloat(cart.getProductPrice()));
                        holder.num.setText(Integer.toString(cart.getQuantity()));

                        double newValue = Double.valueOf(dfZero.format(cart.getTotalPrice()));
                        Map<String, Object> UpData = new HashMap<>();
                        UpData.put("Quantity",Integer.valueOf(cart.getQuantity()));
                        UpData.put("TotalPrice",newValue);

                        FirebaseDatabase.getInstance().getReference().child("Cart List")
                                .child(cart.getUID())
                                .updateChildren(UpData)
                                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                    }else{
                        FirebaseDatabase.getInstance().getReference().child("Cart List")
                                .child(cart.getUID()).removeValue();
                    }
                });

                holder.plusItem.setOnClickListener(v -> {
                    cart.setQuantity(cart.getQuantity()+1);
                    cart.setTotalPrice(cart.getQuantity() * Float.parseFloat(cart.getProductPrice()));

                    holder.num.setText(Integer.toString(cart.getQuantity()));

                    double newValue = Double.valueOf(dfZero.format(cart.getTotalPrice()));
                    Map<String, Object> UpData = new HashMap<>();
                    UpData.put("Quantity",Integer.valueOf(cart.getQuantity()));
                    UpData.put("TotalPrice",newValue);

                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child(cart.getUID())
                            .updateChildren(UpData)
                            .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                });

                holder.picintent.setOnClickListener(v ->{

                    String sTotal =String.valueOf(cart.getTotalPrice());
                    Intent intent = new Intent(CartListActivity.this,AddAddressCheckout.class);
                    intent.putExtra("ProductID", cart.getUID());
                    intent.putExtra("ByOwner", cart.getBy());
                    intent.putExtra("ShopName", cart.getShopName());
                    intent.putExtra("ShopPhoneNo", cart.getShopPhoneNo());
                    intent.putExtra("ShopAdd", cart.getShopAdd());
                    intent.putExtra("ProductPic", cart.getImageUrl());
                    intent.putExtra("ProductName", cart.getProductName());
                    intent.putExtra("TotalPrice", sTotal);
                    intent.putExtra("Quantity", (Serializable) new StringBuilder().append(cart.getQuantity()));
                    intent.putExtra("CusName", cart.getFullName());
                    intent.putExtra("CusPhoneNo", cart.getPhoneNo());
                    intent.putExtra("CusEmail", GlobalOnline.currentOnline.getEmail());
                    startActivity(intent);
                });
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);
                CartViewHolder holder = new CartViewHolder(inflate);
                return holder;

            }
        };
        recyclerViewList.setAdapter(adapt);
        adapt.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapt.stopListening();
    }

//    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver(){
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            double totalBill =  intent.getDoubleExtra("OverPrice",OverPrice);
//            totalFeeTxt.setText(Double.toString(totalBill));
//            content();
//        }
//    };
}