package com.anhduy.bantrasua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anhduy.bantrasua.R;
import com.anhduy.bantrasua.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
   public Context context;
   public ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_moinhat,null);
        ItemHolder itemHolder=new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        Sanpham sanpham=arraysanpham.get(i);
        itemHolder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtgiasanpham.setText("Giá : "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.get()
                .load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.outline_broken_image_black_48)
                .error(R.drawable.outline_warning_black_48)
                .into(itemHolder.imghinhsanpham);


    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtgiasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham=(ImageView)itemView.findViewById(R.id.imageviewsanpham);
            txttensanpham=(TextView)itemView.findViewById(R.id.textviewtensanpham);
            txtgiasanpham=(TextView)itemView.findViewById(R.id.textviewgiasanpham);
        }
    }


}
