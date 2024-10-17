package com.app.appcafedemo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.appcafedemo.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseDatabase database;
    public String TAG ="lyshctah";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        Auth=FirebaseAuth.getInstance();
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }
}