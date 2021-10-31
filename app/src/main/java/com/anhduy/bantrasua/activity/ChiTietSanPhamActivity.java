package com.anhduy.bantrasua.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.model.Giohang;
import com.anhduy.bantrasua.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private TextView txtten, txtgia, txtnmota;
    private ImageView imghinh;
    private Toolbar toolbar;
    private Button bttthem;
    private Spinner spinner;
    public int id = 0;
    public String TenChiTiet = "";
    public int GiaChitiet = 0;
    public String hinhanhchitiet = "";
    public String MoTachitiet = "";
    public int idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        Actiontoolbar();
        GetData();
        CatchEventSpinner();
        EventButtonGioHang();

    }

    private void EventButtonGioHang() {
        bttthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exits = false;
                    for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                        if (MainActivity.manggiohang.get(i).getIdsp() == id) {
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp() > 10) {
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exits = true;
                        }
                    }
                    if (exits == false) {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi1 = soluong * GiaChitiet;
                        MainActivity.manggiohang.add(new Giohang(id, TenChiTiet, giamoi1, hinhanhchitiet, soluong));
                    }
                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong * GiaChitiet;
                    MainActivity.manggiohang.add(new Giohang(id, TenChiTiet, giamoi, hinhanhchitiet, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetData() {
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        TenChiTiet = sanpham.getTensanpham();
        GiaChitiet = sanpham.getGiasanpham();
        hinhanhchitiet = sanpham.getHinhanhsanpham();
        MoTachitiet = sanpham.getMotasanpham();
        idsanpham = sanpham.getIDsanpham();
        int gia = sanpham.getGiasanpham();
        txtten.setText(sanpham.getTensanpham());
        txtnmota.setText(sanpham.getMotasanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá " + decimalFormat.format(gia) + " đ");
        Picasso.get()
                .load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.outline_broken_image_black_48)
                .error(R.drawable.outline_warning_black_48)
                .into(imghinh);
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        spinner = (Spinner) findViewById(R.id.spinner);
        toolbar = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        txtgia = (TextView) findViewById(R.id.textviewgiachitiet);
        txtnmota = (TextView) findViewById(R.id.textviewmotachitiet);
        txtten = (TextView) findViewById(R.id.textviewtenchitiet);
        imghinh = (ImageView) findViewById(R.id.imageviewanhchitiet);
        bttthem = (Button) findViewById(R.id.buttongiohang);
    }
}