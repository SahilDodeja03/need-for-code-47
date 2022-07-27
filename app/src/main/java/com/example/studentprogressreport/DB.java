package com.example.studentprogressreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public DB(@Nullable Context context) {
        super(context, "StudentProgress.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table teacher(id Integer primary key AUTOINCREMENT,name TEXT not null default 'abcd'," +
                "email text not null default 'xyz@gmail.com',address text not null default 'mnop',phone text default '0'," +
                "password text not null default 'cmpn2022')");
        db.execSQL("create table subject(subject_id integer primary key AUTOINCREMENT,subject_name text default 'def' )");
        db.execSQL("create table student(id integer primary key AUTOINCREMENT,roll_no text,name text default 'abcd'," +
                "address text default 'mnop',parent_contact text default '0'" +
                ",current_std text default 10,username text,password text default 'comps2022')");
        db.execSQL("create table mark(id integer primary key AUTOINCREMENT,subject_id integer default 0," +
                "student_id integer default 0," +
                "ut1 integer default 0," +
                "ut2 integer default 0," +
                "sem1 integer default 0," +
                "ut3 integer default 0," +
                "ut4 integer default 0," +
                "sem2 integer default 0,foreign key(subject_id) references subject(subject_id),foreign key(student_id) references " +
                "student(id))");
        db.execSQL("create table teacher_sub(id Integer primary key AUTOINCREMENT,subject_id integer,teacher_id integer,standard text," +
                " foreign key(subject_id) references subject(subject_id),foreign key(teacher_id) references teacher(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean checkLogin(String name,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from teacher where name=? and password=?",
                new String[]{name,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        return false;
    }
    public boolean insertTeacher(String values[])
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        String column[]=new String[]{"name","email","address","phone","password"};
        for(int i=0;i<values.length;i++)
        {
            value.put(column[i],values[i]);
        }
        Long result = db.insert("teacher", null, value);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean insertStudent(String values[])
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        String column[]=new String[]{"roll_no","name","address","parent_contact","current_std","username","password"};
        for(int i=0;i<values.length;i++)
        {
            value.put(column[i],values[i]);
        }
        Long result = db.insert("student", null, value);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public void insertSubject()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sub[]=new String[]{"Maths","History","Science","Geography","English"};
        for(int i=0;i< sub.length;i++)
        {
            ContentValues value = new ContentValues();
            value.put("subject_name",sub[i]);
            Long result = db.insert("subject", null, value);
        }
    }
    public boolean insertTrSub(String teacher,String subject,int std)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select subject_id from subject where subject_name=?",new String[]{subject});
        cursor.moveToNext();
        int sub_id= cursor.getInt(0);
        cursor=db.rawQuery("select id from teacher where name=?",new String[]{teacher});
        cursor.moveToNext();
        int tr_id= cursor.getInt(0);
        ContentValues value = new ContentValues();
        value.put("subject_id",sub_id);
        value.put("teacher_id",tr_id);
        Long result = db.insert("teacher_sub", null, value);
        if (result == -1) {
            return false;
        } else
            return true;

    }
    public Cursor teacherName()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select name from teacher",null);
        if(cursor.getCount()>0)
        {
            return cursor;
        }
        else {

            return null;
        }
    }

    public Cursor getSubjects()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select subject_name from subject",null);
        if(cursor.getCount()>0)
        {
            return cursor;
        }
        else {
            return null;
        }
    }

    public void deleteall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("delete from subject",null);

    }


}