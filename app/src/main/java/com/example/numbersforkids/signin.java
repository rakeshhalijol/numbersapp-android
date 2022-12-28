package com.example.numbersforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {


    Button signin_btn,go_log;
    EditText sname2,spass1,spass2;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        go_log = (Button) findViewById(R.id.go_log);
        signin_btn = (Button) findViewById(R.id.signin_btn);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(signin.this,menu.class));
            finish();
        }

        go_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signin.this,login.class);
                startActivity(intent);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = sname2.getText().toString();
                String pass1 = spass1.getText().toString();
                String pass2 = spass2.getText().toString();

                if (TextUtils.isEmpty(email)){
                    sname2.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(pass1)){
                    sname2.setError("Password required");
                    return;
                }

                if (TextUtils.isEmpty(pass2)){
                    sname2.setError("Confirm password required");
                    return;
                }

                if (pass1 != pass2){
                    spass1.setError("PAssword missmatch");
                    return;
                }

                //register user
                fAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signin.this, "Registration Succesccfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signin.this,menu.class));
                        }
                        else{
                            Toast.makeText(signin.this, "Error occured retry again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });




    }
}