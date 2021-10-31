package com.anhduy.bantrasua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.activity.GioHangActivity;
import com.anhduy.bantrasua.activity.MainActivity;
import com.anhduy.bantrasua.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> manggiohang;

    public GioHangAdapter(Context context, ArrayList<Giohang> manggiohang) {
        this.context = context;
        this.manggiohang = manggiohang;
    }

    @Override
    public int getCount() {
        return manggiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return manggiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class  ViewHolder{
        public static TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public static Button bttplus,bttvalues,bttminus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang=(TextView)convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang=(TextView)convertView.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang=(ImageView)convertView.findViewById(R.id.imagivewanhgiohang);
            viewHolder.bttvalues=(Button)convertView.findViewById(R.id.buttongiohangvalues);
            viewHolder.bttminus=(Button)convertView.findViewById(R.id.buttongiohangminus);
            viewHolder.bttplus=(Button)convertView.findViewById(R.id.buttongiohangplus);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Giohang gioHang= (Giohang) getItem(position);
        viewHolder.txttengiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText("Gía : "+decimalFormat.format(gioHang.giasp)+" Đ");
        Picasso.get()
                .load(gioHang.getHinhanhsp())
                .placeholder(R.drawable.outline_broken_image_black_48)
                .error(R.drawable.outline_warning_black_48)
                .into(viewHolder.imggiohang);
        viewHolder.bttvalues.setText(gioHang.getSoluongsp()+"");
        final int sl= Integer.parseInt(viewHolder.bttvalues.getText().toString());
        if (sl>=10) {
            viewHolder.bttplus.setVisibility(View.INVISIBLE);

        }else if(sl<1){
            viewHolder.bttminus.setVisibility(View.INVISIBLE);

        }else {
            viewHolder.bttminus.setVisibility(View.VISIBLE);
            viewHolder.bttplus.setVisibility(View.VISIBLE);
        }

        viewHolder.bttplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(ViewHolder.bttvalues.getText().toString())+1;
                int slht=MainActivity.manggiohang.get(position).soluongsp;
                long giaht=MainActivity.manggiohang.get(position).giasp;
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat=(giaht*slmoinhat)/slht;
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                ViewHolder.txtgiagiohang.setText("Giá : "+decimalFormat.format(giamoinhat)+" đ");
                GioHangActivity.EventUtil();
                if(slmoinhat>9){
                    ViewHolder.bttvalues.setText(String.valueOf(slmoinhat));
                    ViewHolder.bttminus.setVisibility(View.VISIBLE);
                    ViewHolder.bttplus.setVisibility(View.INVISIBLE);
                }else{
                    ViewHolder.bttvalues.setText(String.valueOf(slmoinhat));
                    ViewHolder.bttminus.setVisibility(View.VISIBLE);
                    ViewHolder.bttplus.setVisibility(View.VISIBLE);
                }
            }
        });
        ViewHolder.bttminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(ViewHolder.bttvalues.getText().toString())-1;
                int slht= MainActivity.manggiohang.get(position).soluongsp;
                long giaht=MainActivity.manggiohang.get(position).giasp;
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);
                long giamoinhat=(giaht*slmoinhat)/slht;
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                ViewHolder.txtgiagiohang.setText("Giá : "+decimalFormat.format(giamoinhat)+" Đ");
                GioHangActivity.EventUtil();
                if(slmoinhat<2){
                    ViewHolder.bttvalues.setText(String.valueOf(slmoinhat));
                    ViewHolder.bttminus.setVisibility(View.INVISIBLE);
                    ViewHolder.bttplus.setVisibility(View.VISIBLE);
                }else{
                    ViewHolder.bttvalues.setText(String.valueOf(slmoinhat));
                    ViewHolder.bttminus.setVisibility(View.VISIBLE);
                    ViewHolder.bttplus.setVisibility(View.VISIBLE);
                }
            }
        });
        return convertView;
    }

}
