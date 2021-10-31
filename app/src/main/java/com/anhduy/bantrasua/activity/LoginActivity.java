package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.databinding.ActivityLoginBinding;
import com.anhduy.bantrasua.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    EditText edtTenDN,edtMK;

    public static int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        anhxa();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=binding.edtUsername.getText().toString().trim();
                String password=binding.edtPassword.getText().toString().trim();
                if (username.isEmpty()||password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    kiemtrataikhoan();
                }
            }
        }));
    }

    private void anhxa() {
        edtMK=binding.edtPassword;
        edtTenDN=binding.edtUsername;
    }

    private void kiemtrataikhoan() {
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.kiemtrataikhoan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("error")) {
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }else{
                    int value=Integer.parseInt(response);
                    id=value;
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,"Lỗi Xảy Ra",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("taikhoan",binding.edtUsername.getText().toString().trim());
                params.put("matkhau",binding.edtPassword.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}