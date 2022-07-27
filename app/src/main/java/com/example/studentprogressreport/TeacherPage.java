package com.example.studentprogressreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherPage extends AppCompatActivity {
    Button marks,whole,particular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_page);
        marks=findViewById(R.id.button);
        whole=findViewById(R.id.button2);
        particular=findViewById(R.id.button3);
        particular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TeacherPage.this,TeacherMarks.class);
                startActivity(intent);
            }
        });
    }
}