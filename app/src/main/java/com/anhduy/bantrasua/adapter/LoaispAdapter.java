package com.anhduy.bantrasua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    public ArrayList<Loaisp> arrayListLoaisp;
    public Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListLoaisp, Context context) {
        this.arrayListLoaisp = arrayListLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLoaisp.get(position);
    }

    private class Viewhorder{
        TextView txttenloaisp;
        ImageView imgloaisp;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhorder ViewHorder;
        if(convertView == null) {
            ViewHorder=new Viewhorder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_listview_loaisp,null);
            ViewHorder.imgloaisp=(ImageView)convertView.findViewById(R.id.imageviewloaisp);
            ViewHorder.txttenloaisp=(TextView)convertView.findViewById(R.id.textviewloaisp);
            convertView.setTag(ViewHorder);
        }else{
            ViewHorder= (Viewhorder) convertView.getTag();
        }
        Loaisp loaisp=(Loaisp)getItem(position);
        ViewHorder.txttenloaisp.setText(loaisp.getTenLoaisp());
        Picasso.get()
                .load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.outline_broken_image_black_48)
                .error(R.drawable.outline_warning_black_48)
                .into(ViewHorder.imgloaisp);
        return convertView;
    }
}
