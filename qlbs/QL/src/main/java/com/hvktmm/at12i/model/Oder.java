package com.hvktmm.at12i.model;

public class Oder {
    private int id;

    private int id_member;

    private int id_depot;

    private int id_user;

    private float price;

    private String day_tryding;

//    private String status_price;

    public Oder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_member() {
        return id_member;
    }

    public void setId_member(int id_member) {
        this.id_member = id_member;
    }

    public int getId_depot() {
        return id_depot;
    }

    public void setId_depot(int id_depot) {
        this.id_depot = id_depot;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDay_tryding() {
        return day_tryding;
    }

    public void setDay_tryding(String day_tryding) {
        this.day_tryding = day_tryding;
    }

//    public String getStatus_price() {
//        return status_price;
//    }
//
//    public void setStatus_price(String status_price) {
//        this.status_price = status_price;
//    }
}
