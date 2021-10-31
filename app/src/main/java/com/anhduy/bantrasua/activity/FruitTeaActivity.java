package com.anhduy.bantrasua.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.adapter.FruitTeaAdapter;
import com.anhduy.bantrasua.databinding.ActivityFruitTeaBinding;
import com.anhduy.bantrasua.model.Sanpham;
import com.anhduy.bantrasua.ultil.CheckConnection;
import com.anhduy.bantrasua.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FruitTeaActivity extends AppCompatActivity {
    private ActivityFruitTeaBinding binding;

    ListView listViewFruittea;
    FruitTeaAdapter fruitTeaAdapter;
    ArrayList<Sanpham> arrayFruittea;
    int idft=0;
    int page=1;
    View footerview;
    boolean isLoading=false;
    boolean limitdata=false;
    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFruitTeaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIdloaisp();
            Actiontoolbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_short(getApplicationContext(),"Lỗi");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang :
                Intent intent=new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Anhxa() {
        listViewFruittea=(ListView)findViewById(R.id.listViewFruitTea);
        arrayFruittea=new ArrayList<>();
        fruitTeaAdapter=new FruitTeaAdapter(getApplicationContext(),arrayFruittea);
        listViewFruittea.setAdapter(fruitTeaAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
    }
    private void GetIdloaisp() {
        idft=getIntent().getIntExtra("idloaisanpham",-1);
    }
    private void Actiontoolbar() {
        setSupportActionBar(binding.toolBarFruitTea);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolBarFruitTea.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void LoadMoreData() {
        listViewFruittea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham",arrayFruittea.get(position));
                startActivity(intent);
            }
        });
        listViewFruittea.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount != 0 && isLoading==false && limitdata==false){
                    isLoading=true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }
    private void GetData(int page) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String duongdan=Server.DuongdanTrasua+String.valueOf(page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null && response.length() != 2){
                    listViewFruittea.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            arrayFruittea.add(new Sanpham(jsonObject.getInt("id")
                                    ,jsonObject.getString("tensp")
                                    ,jsonObject.getInt("giasp")
                                    ,jsonObject.getString("hinhanhsp")
                                    ,jsonObject.getString("motasp")
                                    ,jsonObject.getInt("idsanpham")));
                                    fruitTeaAdapter.notifyDataSetChanged();
                        }
                        fruitTeaAdapter=new FruitTeaAdapter(getApplicationContext(),arrayFruittea);
                        listViewFruittea.setAdapter(fruitTeaAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitdata=true;
                    listViewFruittea.removeFooterView(footerview);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(idft));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listViewFruittea.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}