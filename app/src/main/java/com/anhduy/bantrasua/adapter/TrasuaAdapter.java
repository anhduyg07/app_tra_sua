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

public class TrasuaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Sanpham> arraytrasua;

    public TrasuaAdapter(Context context, ArrayList<Sanpham> arraytrasua) {
        this.context = context;
        this.arraytrasua = arraytrasua;
    }

    @Override
    public int getCount() {
        return arraytrasua.size();
    }

    @Override
    public Object getItem(int position) {
        return arraytrasua.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        private ImageView imghinhtrasua;
        private TextView txttentrasua,txtgiatrasua,txtmotatrasua;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_trasua,null);
            viewHolder.imghinhtrasua=(ImageView)convertView.findViewById(R.id.imageViewTrasua);
            viewHolder.txttentrasua=(TextView) convertView.findViewById(R.id.textViewTenTrasua);
            viewHolder.txtgiatrasua=(TextView)convertView.findViewById(R.id.textViewGiaTrasua);
            viewHolder.txtmotatrasua=(TextView)convertView.findViewById(R.id.textViewMotaTrasua);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txttentrasua.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiatrasua.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.get()
                .load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.outline_broken_image_black_48)
                .error(R.drawable.outline_warning_black_48)
                .into(viewHolder.imghinhtrasua);
        viewHolder.txtmotatrasua.setMaxLines(2);
        viewHolder.txtmotatrasua.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotatrasua.setText(sanpham.getMotasanpham());

        return convertView;
    }
}
