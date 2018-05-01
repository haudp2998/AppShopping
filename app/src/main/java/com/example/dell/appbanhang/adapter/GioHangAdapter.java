package com.example.dell.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dell.appbanhang.R;
import com.example.dell.appbanhang.activity.MainActivity;
import com.example.dell.appbanhang.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public Button btntang,btnvalue,btngiam;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang = (TextView) convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang = (TextView) convertView.findViewById(R.id.textviewgiagiophang);
            viewHolder.imggiohang = (ImageView) convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.btntang = (Button) convertView.findViewById(R.id.buttontang);
            viewHolder.btnvalue = (Button) convertView.findViewById(R.id.buttonvalue);
            viewHolder.btngiam = (Button) convertView.findViewById(R.id.buttongiam);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Giohang giohang = (Giohang) getItem(position);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp()) +" Đ");
        Picasso.with(context).load(giohang.getHinhanh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalue.setText(giohang.getSoluong()+"");
        int sl= Integer.parseInt(viewHolder.btnvalue.getText().toString());
            if(sl>=10){
                viewHolder.btntang.setVisibility(View.INVISIBLE);
                viewHolder.btngiam.setVisibility(View.VISIBLE);
            }
            else if(sl<=1){
                viewHolder.btngiam.setVisibility(View.INVISIBLE);
            }
            else{
                viewHolder.btngiam.setVisibility(View.VISIBLE);
                viewHolder.btntang.setVisibility(View.VISIBLE);
            }

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btntang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int slmoinhat = Integer.parseInt(finalViewHolder.btnvalue.getText().toString()) +1;
                    int slhientai = MainActivity.manggiohang.get(position).getSoluong();
                    long giaht = MainActivity.manggiohang.get(position).getGiasp();
                    MainActivity.manggiohang.get(position).setSoluong(slmoinhat);
                    long giamoinhat = (giaht * slmoinhat) / slhientai;
                    MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) +" Đ");
                    com.example.dell.appbanhang.activity.Giohang.EventUltil();
                    if(slmoinhat > 9){
                        finalViewHolder.btntang.setVisibility(View.INVISIBLE);
                        finalViewHolder.btngiam.setVisibility(View.VISIBLE);
                        finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                    }
                    else{
                        finalViewHolder.btntang.setVisibility(View.VISIBLE);
                        finalViewHolder.btngiam.setVisibility(View.VISIBLE);
                        finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                    }
                }
            });
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btngiam.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int slmoinhat = Integer.parseInt(finalViewHolder1.btnvalue.getText().toString()) -1;
                  int slhientai = MainActivity.manggiohang.get(position).getSoluong();
                  long giaht = MainActivity.manggiohang.get(position).getGiasp();
                  MainActivity.manggiohang.get(position).setSoluong(slmoinhat);
                  long giamoinhat = (giaht * slmoinhat) / slhientai;
                  MainActivity.manggiohang.get(position).setGiasp(giamoinhat);
                  DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                  finalViewHolder1.txtgiagiohang.setText(decimalFormat.format(giamoinhat) +" Đ");
                  com.example.dell.appbanhang.activity.Giohang.EventUltil();
                  if(slmoinhat < 2){
                      finalViewHolder1.btntang.setVisibility(View.VISIBLE);
                      finalViewHolder1.btngiam.setVisibility(View.INVISIBLE);
                      finalViewHolder1.btnvalue.setText(String.valueOf(slmoinhat));
                  }
                  else{
                      finalViewHolder1.btntang.setVisibility(View.VISIBLE);
                      finalViewHolder1.btngiam.setVisibility(View.VISIBLE);
                      finalViewHolder1.btnvalue.setText(String.valueOf(slmoinhat));
                  }
              }
          });

        return convertView;
    }
}
