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
import com.anhduy.bantrasua.databinding.ActivityRegisterBinding;
import com.anhduy.bantrasua.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    private EditText edthoten,edtemail,edtmatkhau,edtnhaplaimatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Anhxa();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edtemail.getText().toString().trim();
                String hoten=edthoten.getText().toString().trim();
                String matkhau=edtmatkhau.getText().toString().trim();
                String nhaplaimatkhau=edtnhaplaimatkhau.getText().toString().trim();
                if(email.isEmpty()||hoten.isEmpty()||matkhau.isEmpty()||nhaplaimatkhau.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else if(matkhau.equals(nhaplaimatkhau)){
                    themtaikhoan();
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Anhxa() {
        edthoten=binding.edtName;
        edtemail=binding.edtEmail;
        edtmatkhau=binding.edtPassword;
        edtnhaplaimatkhau=binding.edtRepassword;
    }

    private void themtaikhoan() {
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.duongdandangkitaikhoan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")) {
                    Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }else if(response.trim().equals("Tài Khoản Đã Tồn Tại")){
                    Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();
                }else if(response.trim().equals("error")){
                    Toast.makeText(RegisterActivity.this, "Có lỗi xảy ra", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"Lỗi Xảy Ra",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("hoten",edthoten.getText().toString().trim());
                params.put("email",edtemail.getText().toString().trim());
                params.put("matkhau",edtmatkhau.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}