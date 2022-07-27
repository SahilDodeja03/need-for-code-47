package com.example.studentprogressreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetails extends AppCompatActivity {
    EditText edit[]=new EditText[5];
    String values[]=new String[5];
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        add=findViewById(R.id.add2);
        edit[0]=findViewById(R.id.roll_no);
        edit[1]=findViewById(R.id.name1);
        edit[2]=findViewById(R.id.address1);
        edit[3]=findViewById(R.id.phone1);
        edit[4]=findViewById(R.id.currentStd);
        DB db=new DB(StudentDetails.this);
//        db.insertSubject();
//        db.deleteall();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;
                for(i=0;i< values.length;i++) {
                    if (edit[i].getText().toString().equals("")) {
                        Toast.makeText(StudentDetails.this, "Please add all the details", Toast.LENGTH_SHORT).show();
                        break;
                    } else
                        values[i] = edit[i].getText().toString();
                }
                if(i==values.length)
                {
                    DB db=new DB(StudentDetails.this);
                    if(db.insertStudent(values))
                    {
                        Toast.makeText(StudentDetails.this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(StudentDetails.this,AdminPage.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(StudentDetails.this, "Please Try Again", Toast.LENGTH_SHORT).show();
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