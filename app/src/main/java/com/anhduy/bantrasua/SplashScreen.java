package com.anhduy.bantrasua;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anhduy.bantrasua.activity.MainActivity;
import com.anhduy.bantrasua.activity.WelcomeActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}