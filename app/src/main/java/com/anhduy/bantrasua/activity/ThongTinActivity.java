package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.anhduy.bantrasua.R;

public class ThongTinActivity extends AppCompatActivity {
    Toolbar toolbarthongtin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        Anhxa();
        Actiontoolbar();
    }

    private void Anhxa() {
        toolbarthongtin=(Toolbar) findViewById(R.id.toolbarthongtin);
    }
    private void Actiontoolbar() {
        setSupportActionBar(toolbarthongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}