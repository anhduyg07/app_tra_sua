package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.adapter.GioHangAdapter;
import com.anhduy.bantrasua.databinding.ActivityGioHangBinding;
import com.anhduy.bantrasua.ultil.CheckConnection;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    private ActivityGioHangBinding binding;
    private ListView lvgiohang;
    private TextView txtthongbao;
    public static TextView txttongtien;
    private static long tongtien=0;
    private Toolbar toolbargiohang;
    private GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGioHangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Anhxa();
        CheckData();
        Actiontoolbar();
        EventUtil();
        CatchOnItemListView();

        binding.buttontieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        binding.buttonthanhtoanngiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhtoan();
            }
        });

    }

    private void thanhtoan() {
        if(MainActivity.manggiohang.isEmpty()){
            CheckConnection.ShowToast_short(getApplicationContext(),"Bạn chưa có sản phẩm trong giỏ hàng");
        }
        else {
            Intent intent=new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
            startActivity(intent);
        }
    }


    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(GioHangActivity.this,R.style.AlertDialogStyle);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa sản phẩm này không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size()<=0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EventUtil();
                            if(MainActivity.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EventUtil();
                            }

                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        EventUtil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUtil() {
        tongtien=0;
        for(int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText("Giá : "+decimalFormat.format(tongtien)+" Đ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <= 0){
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else{
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void Actiontoolbar() {
        setSupportActionBar(binding.toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang=binding.listviewgiohang;
        txttongtien=binding.textviewgiatrigiohang;
        txtthongbao=binding.textviewthongbao;
        gioHangAdapter=new GioHangAdapter(GioHangActivity.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(gioHangAdapter);
    }
}