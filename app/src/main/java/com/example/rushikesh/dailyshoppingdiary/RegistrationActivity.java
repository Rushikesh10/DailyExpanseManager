package com.example.rushikesh.dailyshoppingdiary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    EditText email,pass;
    TextView signin;
    Button btn_Reg;

    FirebaseAuth mAuth;
    ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Context context;
        mDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.email_reg);
        pass=findViewById(R.id.password_reg);
        signin=findViewById(R.id.signin_txt);
        btn_Reg=findViewById(R.id.btn_reg);

        btn_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail=email.getText().toString().trim();
                String mPass=pass.getText().toString().trim();
                if (TextUtils.isEmpty(mPass)){
                    pass.setError("Required Password");
                    return;
                }
                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Enter Email");
                    return;
                }
                mDialog.setMessage("Processing Please Wait");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String action;
                            Uri uri;
                            Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();

                        }
                    }
                });
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
