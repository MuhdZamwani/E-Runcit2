package com.example.customerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CustomerSignUp extends AppCompatActivity {

    private TextInputEditText InputFullName, InputUserName, InputEmail, InputPhoneNo, InputPass, InputConPass;
    private Button customeralreadysignup, firstcustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_customer_sign_up);

        InputFullName = findViewById(R.id.CustFullName);
        InputUserName = findViewById(R.id.CustUserName);
        InputEmail = findViewById(R.id.CustEmail);
        InputPhoneNo = findViewById(R.id.CustPhoneNo);
        InputPass = findViewById(R.id.CustPassword);
        InputConPass = findViewById(R.id.CustConPassword);


        firstcustomer = findViewById(R.id.CustomerFirstSignUp);
        firstcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerFirstSignUp();
            }
        });

        customeralreadysignup = (Button) findViewById(R.id.CustomerAlreadySignUp);
        customeralreadysignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerAlreadySignUp();
            }
        });

    }

    private boolean customerFirstSignUp() {
        String FullName = InputFullName.getText().toString();
        String UserName = InputUserName.getText().toString();
        String Email = InputEmail.getText().toString();
        String PhoneNo = InputPhoneNo.getText().toString();
        String Pass = InputPass.getText().toString();
        String ConPass = InputConPass.getText().toString();

        if (TextUtils.isEmpty(FullName))
        {
            //Toast.makeText(this, "Please write Shop Name", Toast.LENGTH_SHORT).show();
            showError(InputFullName,"Please Enter Full name");
            return false;
        }
        else if (TextUtils.isEmpty(UserName))
        {
            //Toast.makeText(this, "Please write Username...", Toast.LENGTH_SHORT).show();
            showError(InputUserName,"Please Enter Username");
            return false;
        }
        else if (TextUtils.isEmpty(Email))
        {
            //Toast.makeText(this, "Please write Owner Email...", Toast.LENGTH_SHORT).show();
            showError(InputEmail,"Please write your Email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            //Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            showError(InputEmail,"Invalid Email");
            return false;
        }
        else if (TextUtils.isEmpty(PhoneNo))
        {
            //Toast.makeText(this, "Please write Phone Number...", Toast.LENGTH_SHORT).show();
            showError(InputPhoneNo,"Please write Phone Number");
            return false;
        }
        else if (TextUtils.isEmpty(Pass))
        {
            //Toast.makeText(this, "Please Enter Password...", Toast.LENGTH_SHORT).show();
            showError(InputPass,"Please Enter Password");
            return false;

        }
        else if(Pass.length() <6){
            //Toast.makeText(this, "Password must contains 9 character...", Toast.LENGTH_SHORT).show();
            showError(InputPass,"Password min 6 character");
            InputPass.setTransformationMethod(null);
            return false;
        }
        else if (TextUtils.isEmpty(ConPass))
        {
            //Toast.makeText(this, "Please Enter Same Password ...", Toast.LENGTH_SHORT).show();
            showError(InputConPass,"Please Enter Same Password");
            return false;
        }
        else if (!Pass.equals(ConPass))
        {
            //Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
            showError(InputConPass,"Password not matching");
            InputPass.setTransformationMethod(null);
            InputConPass.setTransformationMethod(null);
            return false;
        }
        else
        {
            AllowAccessCustToAccount(FullName, UserName, Email, PhoneNo, ConPass);
        }
        return false;
    }

    private void AllowAccessCustToAccount(final String fullName,
                                          final String userName,
                                          final String email,
                                          final String phoneNo,
                                          final String ConPass) {

        final DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Customer").child(userName).exists())){
                    HashMap<String, Object> custData = new HashMap<>();
                    custData.put("FullName", fullName);
                    custData.put("Username", userName);
                    custData.put("Email", email);
                    custData.put("PhoneNo", phoneNo);
                    custData.put("Password", ConPass);
                    reff.child("Customer").child(userName).child("Customer Details").updateChildren(custData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(CustomerSignUp.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CustomerSignUp.this, CustomerLogin.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(CustomerSignUp.this, "An Error has occurred!!Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(CustomerSignUp.this, "Username already exists!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }

    public void openCustomerAlreadySignUp(){
        Intent intent = new Intent(this, CustomerLogin.class);
        startActivity(intent);
    }
}