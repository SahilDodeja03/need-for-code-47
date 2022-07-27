package com.example.studentprogressreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    Button student,admin,teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        student=findViewById(R.id.button6);
        admin=findViewById(R.id.button5);
        teacher=findViewById(R.id.button4);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,StudentLogin.class);

                intent.putExtra("login","Student Login");
                startActivity(intent);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,StudentLogin.class);
                intent.putExtra("login","Admin Login");
                startActivity(intent);

            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginPage.this,StudentLogin.class);
                intent.putExtra("login","Teacher Login");
                startActivity(intent);

            }
        });
    }
}