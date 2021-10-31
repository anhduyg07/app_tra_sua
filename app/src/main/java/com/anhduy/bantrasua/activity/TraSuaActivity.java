package com.anhduy.bantrasua.activity;

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
import androidx.appcompat.widget.Toolbar;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.adapter.TrasuaAdapter;
import com.anhduy.bantrasua.databinding.ActivityFruitTeaBinding;
import com.anhduy.bantrasua.databinding.ActivityTraSuaBinding;
import com.anhduy.bantrasua.model.Sanpham;
import com.anhduy.bantrasua.ultil.CheckConnection;
import com.anhduy.bantrasua.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TraSuaActivity extends AppCompatActivity {
    private ActivityTraSuaBinding binding;
    Toolbar toolbarTrasua;
    ListView listViewTrasua;
    TrasuaAdapter trasuaAdapter;
    ArrayList<Sanpham> arrayTrasua;
    int idts=0;
    int page=1;
    View footerview;
    boolean isLoading=false;
    boolean limitdata=false;
    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTraSuaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIdloaisp();
            Actiontoolbar();
            GetData(page);
            LoadmoreData();
        }else {
            CheckConnection.ShowToast_short(getApplicationContext(),"Lỗi");
        }
    }

    //Load thêm dữ liệu khi kéo xuống
    private void LoadmoreData() {
        listViewTrasua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham",arrayTrasua.get(position));
                startActivity(intent);
            }
        });
        listViewTrasua.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem +visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading==false && limitdata ==false){
                    isLoading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    //Lấy dữ liệu
    private void GetData(int page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.DuongdanTrasua +String.valueOf(page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null && response.length() != 2){
                    listViewTrasua.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            arrayTrasua.add(new Sanpham(jsonObject.getInt("id")
                                    ,jsonObject.getString("tensp")
                                    ,jsonObject.getInt("giasp")
                                    ,jsonObject.getString("hinhanhsp")
                                    ,jsonObject.getString("motasp")
                                    ,jsonObject.getInt("idsanpham")));
                            trasuaAdapter.notifyDataSetChanged();
                        }
                        trasuaAdapter=new TrasuaAdapter(getApplicationContext(),arrayTrasua);
                        listViewTrasua.setAdapter(trasuaAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }else{
                    limitdata=true;
                    listViewTrasua.removeFooterView(footerview);
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
                param.put("idsanpham",String.valueOf(idts));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbarTrasua);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTrasua.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdloaisp() {
        idts=getIntent().getIntExtra("idloaisanpham",-1);
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
        toolbarTrasua=binding.toolBarTrasua;
        listViewTrasua=binding.listViewTrasua;
        arrayTrasua=new ArrayList<>();
        trasuaAdapter=new TrasuaAdapter(getApplicationContext(),arrayTrasua);
        listViewTrasua.setAdapter(trasuaAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listViewTrasua.addFooterView(footerview);
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
        public void run(){
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