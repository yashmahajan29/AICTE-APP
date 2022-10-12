package com.example.aicte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Student_dash extends AppCompatActivity {

    private Button signOut , fillfrm;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);

        signOut = findViewById(R.id.student_out);
        fillfrm = findViewById(R.id.fill_det);

        mAuth = FirebaseAuth.getInstance();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Student_dash.this, HomeActivity.class));
                finish();
            }
        });
        fillfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student_dash.this, Stud_Form_Activity.class));
            }
        });
    }
}