package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.anhduy.bantrasua.R;

public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbarlienhe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        Anhxa();
        Actiontoolbar();
    }

    private void Anhxa() {
    toolbarlienhe=(Toolbar)findViewById(R.id.toolbarlienhe);
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbarlienhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}