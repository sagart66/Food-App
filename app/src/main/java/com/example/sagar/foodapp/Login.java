package com.example.sagar.foodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.foodapp.Common.Common;
import com.example.sagar.foodapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;

public class Login extends AppCompatActivity {

    EditText edtPhone;
    EditText edtpassword;
    Button btnSignIn;
    SpotsDialog waitingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPhone = (EditText)findViewById(R.id.edtPhone);
        edtpassword = (EditText)findViewById(R.id.edtPass);
        btnSignIn = (Button)findViewById(R.id.btnSignIn1);

        waitingDialog = new SpotsDialog(Login.this);
        //waitingDialog.setMessage("Signing In");


        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitingDialog.show();
                if(validate(edtPhone.getText().toString(),edtpassword.getText().toString())){
                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            waitingDialog.dismiss();
                            if(user!=null && user.getPassword().equals(edtpassword.getText().toString())){
                                Common.currentUser=user;
                                startActivity(new Intent(Login.this,Home.class));
                                finish();
                                //Toast.makeText(Login.this,"Login successful",Toast.LENGTH_SHORT).show();
                                }else if(user!=null){
                                Toast.makeText(Login.this, "Login Failed!! ", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Login.this, "User Doesn't exists", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }
        });
    }

    //validate mobile number and password
    private boolean validate(String s, String s1) {
        if(s.length()==10 && s1.length()>5)
        return true;
        waitingDialog.dismiss();
        Toast.makeText(this, "Enter valid credentials", Toast.LENGTH_SHORT).show();
        return false;
    }
}
