package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anhduy.bantrasua.NotificationChannel;
import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.databinding.ActivityThongTinKhachHangBinding;
import com.anhduy.bantrasua.ultil.CheckConnection;
import com.anhduy.bantrasua.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    private ActivityThongTinKhachHangBinding binding;

    private String tenkh, diachi,sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityThongTinKhachHangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonhuykhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.buttonxacnhankhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhan();
            }
        });
    }

    private void thongbao() {
        Notification notification = new NotificationCompat.Builder(this, NotificationChannel.CHANNEL_ID)
                .setSmallIcon(R.drawable.outline_notifications_24)
                .setContentTitle("Thành công")
                .setContentText("Bạn đã đặt hàng thành công")
                .build();
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,notification);
    }

    private void xacnhan() {
        tenkh=binding.edittexttenkhachhang.getText().toString().trim();
        diachi=binding.edittextdiachikhachhang.getText().toString().trim();
        sdt=binding.edittextsdtkhachhang.getText().toString().trim();
        if(diachi.length()>0 && sdt.length()>0 && tenkh.length()>0){
            final RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.DuongdanDonhang, new Response.Listener<String>() {
                @Override
                public void onResponse(final String madonhang) {
                    Log.d("mahoadon",madonhang);
                    if(Integer.parseInt(madonhang)>0){
                        RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                        StringRequest  stringRequest1=new StringRequest(Request.Method.POST, Server.DuongdanChitietdonhang, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    MainActivity.manggiohang.clear();
//                                    CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã đặt hàng thành công");
                                    startActivity(new Intent(getApplicationContext(),OrdersuccessActivity.class));
                                    thongbao();
//                                    CheckConnection.ShowToast_short(getApplicationContext(),"Mời bạn tiếp tục mua sản phẩm");
                                }else{
                                    CheckConnection.ShowToast_short(getApplicationContext(),"Lỗi dữ liệu");
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                JSONArray jsonArray=new JSONArray();
                                for(int i=0;i<MainActivity.manggiohang.size();i++){
                                    JSONObject object=new JSONObject();
                                    try {
                                        object.put("madonhang",madonhang);
                                        object.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                        object.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                        object.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                        object.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArray.put(object);

                                }
                                HashMap<String,String> hashMap=new HashMap<String, String>();
                                hashMap.put("json",jsonArray.toString());
                                return hashMap;
                            }
                        };
                        queue.add(stringRequest1);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> hashMap=new HashMap<String,String>();
                    hashMap.put("tenkhachhang",tenkh);
                    hashMap.put("sodienthoai",sdt);
                    hashMap.put("diachi", diachi);
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
        }else{
            CheckConnection.ShowToast_short(getApplicationContext(),"Vui lòng điền đầy đủ thông tin");
        }
    }



}