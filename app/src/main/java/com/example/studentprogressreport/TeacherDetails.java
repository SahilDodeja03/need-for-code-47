package com.example.studentprogressreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherDetails extends AppCompatActivity {
    EditText edit[]=new EditText[4];
    String values[]=new String[4];
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        add=findViewById(R.id.add2);
        edit[0]=findViewById(R.id.name);
        edit[1]=findViewById(R.id.email);
        edit[2]=findViewById(R.id.address);
        edit[3]=findViewById(R.id.phone);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;
                for(i=0;i< values.length;i++) {
                    if (edit[i].getText().toString().equals("")) {
                        Toast.makeText(TeacherDetails.this, "Please add all the details", Toast.LENGTH_SHORT).show();
                    } else
                        values[i] = edit[i].getText().toString();
                }
                if(i==values.length)
                {
                    DB db=new DB(TeacherDetails.this);
                    if(db.insertTeacher(values))
                    {
                        Toast.makeText(TeacherDetails.this, "Teacher Added Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(TeacherDetails.this,AdminPage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(TeacherDetails.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                        for(int j=0;j< values.length;j++)
                        {
                            edit[j].setText("");
                        }
                    }
                }
                else
                {
                    for(int j=0;j< values.length;j++)
                    {
                        edit[j].setText("");
                    }
                }
            }

        });
    }
}