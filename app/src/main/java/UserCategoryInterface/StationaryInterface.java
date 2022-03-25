package UserCategoryInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customerinterface.CartListActivity;
import com.example.customerinterface.MainActivityCustomer;
import com.example.customerinterface.R;
import com.example.customerinterface.UserManualInterface;
import com.example.customerinterface.UserMyOrderInterface;
import com.example.customerinterface.UserProfileInterface;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import Adapter.StationaryAdapter;
import Model.Products;

public class StationaryInterface extends AppCompatActivity {

    private ImageButton cushome;
    private ImageButton cusprofile;
    private ImageButton cusorder;
    private ImageButton cusmanual;
    private ImageButton cuscart;

    private RecyclerView recyclerViewStationary;
    private StationaryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.ct_stationary_interface);

        cushome = findViewById(R.id.CusHomeBtnStationary);
        cushome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerHomeInterface();
            }
        });

        cusprofile = findViewById(R.id.CusProfileBtnStationary);
        cusprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerProfileInterface();
            }
        });

        cusorder = findViewById(R.id.CusMyOrderBtnStationary);
        cusorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerMyOrderInterface();
            }
        });

        cusmanual = findViewById(R.id.CusManualBtnStationary);
        cusmanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManualInterface();
            }
        });

        cuscart = (ImageButton) findViewById(R.id.card_btn_stationary);
        cuscart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerCartInterface();
            }
        });

        DisStationaryPro();
    }

    private void DisStationaryPro() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewStationary = findViewById(R.id.stationarylistitem);
        recyclerViewStationary.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>().
                setQuery(FirebaseDatabase.getInstance()
                        .getReference().child("Products").orderByChild("Category").equalTo("Stationary"),Products.class)
                .build();

        adapter = new StationaryAdapter(options);
        recyclerViewStationary.setAdapter(adapter);
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

    public void openCustomerCartInterface(){
        Intent intent = new Intent(this, CartListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}