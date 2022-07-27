package com.example.studentprogressreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminPage extends AppCompatActivity {
    FloatingActionButton teacher,student,subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        teacher=findViewById(R.id.teacherAdd);
        student=findViewById(R.id.studentAdd);
        subject=findViewById(R.id.floatingActionButton4);
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminPage.this,TeacherDetails.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminPage.this,StudentDetails.class);
                startActivity(intent);
            }
        });

        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminPage.this,SubjectAllocations.class);
                startActivity(intent);
            }
        });
    }
}