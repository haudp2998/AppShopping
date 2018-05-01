package com.example.dell.appbanhang.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.dell.appbanhang.R;
import com.example.dell.appbanhang.adapter.GioHangAdapter;
import com.example.dell.appbanhang.util.CheckInternet;

import java.text.DecimalFormat;

public class Giohang extends AppCompatActivity {
    ListView listViewgiohang;
    TextView textViewthongbao;
    static TextView textViewtongtien;
    Button btnthanhtoan,btntieptucmuahang;
    Toolbar toolbargiohang;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EventUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHang.class);
                    startActivity(intent);
                }
                else{
                    CheckInternet.showToast_short(getApplicationContext(),"Giỏ hàng của bạn không có sản phẩm để thanh toán");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
                builder.setTitle("Xác nhận xoá sản phẩm");
                builder.setMessage("Bạn có muốn xoá sản phẩm này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size() <=0){
                            textViewthongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            MainActivity.manggiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.manggiohang.size() <= 0){
                                textViewthongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                textViewthongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien =0;
        for(int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewtongtien.setText(decimalFormat.format(tongtien) + " Đ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <=0){
            gioHangAdapter.notifyDataSetChanged();
            textViewthongbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);
        }
        else{
            gioHangAdapter.notifyDataSetChanged();
            textViewthongbao.setVisibility(View.INVISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        listViewgiohang = (ListView) findViewById(R.id.listviewgiohang);
        textViewthongbao = (TextView) findViewById(R.id.textviewthongbao);
        textViewtongtien = (TextView) findViewById(R.id.textviewtongtien);
        btnthanhtoan = (Button) findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmuahang = (Button) findViewById(R.id.buttontieptucmuahang);
        toolbargiohang = (Toolbar) findViewById(R.id.toolbargiohhang);
        gioHangAdapter = new GioHangAdapter(Giohang.this,MainActivity.manggiohang);
        listViewgiohang.setAdapter(gioHangAdapter);
    }
}
