package com.anhduy.bantrasua.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FruitTeaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Sanpham> arrayfruittea;

    public FruitTeaAdapter(Context context, ArrayList<Sanpham> arrayfruittea) {
        this.context = context;
        this.arrayfruittea = arrayfruittea;
    }

    @Override
    public int getCount() {
        return arrayfruittea.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayfruittea.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        private ImageView imghinhfruittea;
        private TextView txttenfruittea,txtgiafruittea,txtmotafruittea;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_fruit_tea,null);
            viewHolder.imghinhfruittea=(ImageView)convertView.findViewById(R.id.imageViewFruitTea);
            viewHolder.txttenfruittea=(TextView) convertView.findViewById(R.id.textViewTenFruitTea);
            viewHolder.txtgiafruittea=(TextView)convertView.findViewById(R.id.textViewGiaFruitTea);
            viewHolder.txtmotafruittea=(TextView)convertView.findViewById(R.id.textViewMotaFruitTea);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txttenfruittea.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiafruittea.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.get()
                .load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.outline_broken_image_black_48)
                .error(R.drawable.outline_warning_black_48)
                .into(viewHolder.imghinhfruittea);
        viewHolder.txtmotafruittea.setMaxLines(2);
        viewHolder.txtmotafruittea.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotafruittea.setText(sanpham.getMotasanpham());

        return convertView;
    }
}
