package com.anhduy.bantrasua;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anhduy.bantrasua.activity.MainActivity;
import com.anhduy.bantrasua.activity.WelcomeActivity;
import com.anhduy.bantrasua.ultil.CheckConnection;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            Intent intent=new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            CheckConnection.ShowToast_short(getApplicationContext(),"Bạn Hãy Kiểm Tra Lại Kết Nối");
            finish();
        }


    }
}