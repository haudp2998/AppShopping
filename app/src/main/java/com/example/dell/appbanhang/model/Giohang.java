package com.example.dell.appbanhang.model;

public class Giohang {
    public int idsp;
    public String tensp;
    public long giasp;
    public String hinhanh;
    public int soluongsp;

    public Giohang(int idsp, String tensp, long giasp, String hinhanh, int soluong) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.soluongsp = soluong;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoluong() {
        return soluongsp;
    }

    public void setSoluong(int soluong) {
        this.soluongsp = soluong;
    }
}
