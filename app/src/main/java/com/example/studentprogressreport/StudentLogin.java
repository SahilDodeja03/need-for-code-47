package com.example.studentprogressreport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentLogin extends AppCompatActivity {
    //ImageView image;
    TextView text;
    EditText name,password;
    String login_type;
    Button sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        // image=findViewById(R.id.imageView3);
        text=findViewById(R.id.textView2);
        sb=findViewById(R.id.add2);
        name=findViewById(R.id.name);
        password=findViewById(R.id.editTextTextPassword);

        Intent intent= getIntent();
        login_type=intent.getStringExtra("login");
        Log.d("mytage"," "+login_type);

        if(login_type.equals("Student Login")){
            //image.setImageResource(R.drawable.img_5);
            text.setText("Student Login");

        }
        else if(login_type.equals("Admin Login")){
            //image.setImageResource(R.drawable.img_6);
            text.setText("Admin Login");
        }
        else{
            //image.setImageResource(R.drawable.img_6);
            text.setText("Teacher Login");


        }
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_type.equals("Teacher Login"))
                {
                    String name2= name.getText().toString();
                    String password2=password.getText().toString();
                    DB db=new DB(StudentLogin.this);
                    if(db.checkLogin(name2,password2))
                    {
                        Intent i=new Intent(StudentLogin.this,TeacherPage.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(StudentLogin.this, "Name Incorrect or password ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent i=new Intent(StudentLogin.this,AdminPage.class);
                    startActivity(i);
                }

            }
        });
    }
}