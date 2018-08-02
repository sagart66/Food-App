package com.example.sagar.foodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.foodapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dmax.dialog.SpotsDialog;

public class SignUp extends AppCompatActivity {

    EditText edtPhone;
    EditText edtName;
    EditText edtPassword;
    Button btnSignUp;
    SpotsDialog waitingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtPhone=(EditText)findViewById(R.id.edtPhoneSignUP);
        edtName=(EditText)findViewById(R.id.edtNameSignUP);
        edtPassword=(EditText)findViewById(R.id.edtPassSignUp);
        btnSignUp = (Button)findViewById(R.id.btnSignUp1);
        waitingDialog = new SpotsDialog(SignUp.this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference("user");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waitingDialog.show();
                if(validate(edtPhone.getText().toString(),edtName.getText().toString(),edtPassword.getText().toString())){
                     table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            waitingDialog.dismiss();
                            if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                                Toast.makeText(SignUp.this, "Phone Number already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                User user= new User(edtName.getText().toString(),edtPassword.getText().toString());
                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "Registration Successful ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this,MainActivity.class));

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
//validate as per requirement
    private boolean validate(String s, String s1, String s2) {
        if(s.length()==10 && s1.length()>5 && s2.length()>5)
            return true;
        waitingDialog.dismiss();
        return false;

    }
}
