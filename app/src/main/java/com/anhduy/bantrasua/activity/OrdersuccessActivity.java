package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.databinding.ActivityOrdersuccessBinding;

public class OrdersuccessActivity extends AppCompatActivity {
    private ActivityOrdersuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrdersuccessActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}