package com.example.aicte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in_activity_faculty extends AppCompatActivity {

    private EditText mEmail , mPass;
    private TextView mTextView;
    private Button signInBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_faculty);

        mEmail = findViewById(R.id.email_signin);
        mPass = findViewById(R.id.passsignin);
        mTextView = findViewById(R.id.textView2);
        signInBtn = findViewById(R.id.signin_btn);

        mAuth = FirebaseAuth.getInstance();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_in_activity_faculty.this , MainActivity.class));
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }
    private void loginUser() {
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
               mAuth.signInWithEmailAndPassword(email , pass)
                       .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                           @Override
                           public void onSuccess(AuthResult authResult) {
                               Toast.makeText(Sign_in_activity_faculty.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(Sign_in_activity_faculty.this, faculty_dash.class));
                               finish();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(Sign_in_activity_faculty.this, "Login Failed", Toast.LENGTH_SHORT).show();
                           }
                       });
            }else{
                mPass.setError("Empty fields are not allowed");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty fields are not allowed");
        }else{
            mEmail.setError("Please Enter Correct Email");
        }
    }
}