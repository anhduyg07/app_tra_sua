package com.anhduy.bantrasua.model;

public class Loaisp {
    public int id;
    public String TenLoaisp;
    public  String Hinhanhloaisp;

    public Loaisp(int id, String tenLoaisp, String hinhanhloaisp) {
        this.id = id;
        TenLoaisp = tenLoaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaisp() {
        return TenLoaisp;
    }

    public void setTenLoaisp(String tenLoaisp) {
        TenLoaisp = tenLoaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }
}
