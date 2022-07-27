package com.example.studentprogressreport;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SubjectAllocations extends AppCompatActivity {
    Spinner spinner,spinner_subjects,standar;
    Button add;
    int std;
    String teacher,subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_allocations);
        spinner=findViewById(R.id.spinner4);
        standar=findViewById(R.id.spinner6);
        spinner_subjects=findViewById(R.id.spinner5);
        add=findViewById(R.id.add2);
        DB db=new DB(this);
        Cursor c=db.teacherName();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(" ");
        if(c==null)
        {
            Toast.makeText(this, "Enter teachers first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(c.moveToNext())
            {
                arrayList.add(c.getString(0));
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    teacher = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + teacher,          Toast.LENGTH_LONG).show();
                }
                @Override
                public void onNothingSelected(AdapterView <?> parent) {
                }
            });
            c=db.getSubjects();
            if(c==null)
            {
                Toast.makeText(this, "Enter teachers first", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ArrayList<String> arrayList1 = new ArrayList<>();
                arrayList1.add(" ");
                while(c.moveToNext())
                {
                    arrayList1.add(c.getString(0));
                }
                ArrayAdapter<String> arrayAdapter1= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList1);
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_subjects.setAdapter(arrayAdapter1);
                spinner_subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        subject = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "Selected: " + subject, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView <?> parent) {
                    }
                });
            }
        }
        ArrayList<String> arrayList3 = new ArrayList<>();
        arrayList3.add(" ");
        for(int i=1;i<11;i++)
        {
            arrayList3.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList3);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        standar.setAdapter(arrayAdapter2);
        standar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String standard = parent.getItemAtPosition(position).toString();
                if(standard.equals(" "))
                {
                    std=0;
                }
                else
                    std=Integer.parseInt(standard);

                Toast.makeText(parent.getContext(), "Selected: " + standard, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.insertTrSub(teacher,subject,std))
                {
                    Toast.makeText(SubjectAllocations.this, " Subject alloted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SubjectAllocations.this,AdminPage.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(SubjectAllocations.this, "Try again", Toast.LENGTH_SHORT).show();

            }
        });
    }
}