package com.hvktmm.at12i.model;

public class History_buy {
    private int id;
    private int id_member;
    private int id_oder;
    private int id_depot;
    private int number_of_paint;
    private float price;

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

    public int getId_oder() {
        return id_oder;
    }

    public void setId_oder(int id_oder) {
        this.id_oder = id_oder;
    }

    public int getId_depot() {
        return id_depot;
    }

    public void setId_depot(int id_depot) {
        this.id_depot = id_depot;
    }

    public int getNumber_of_paint() {
        return number_of_paint;
    }

    public void setNumber_of_paint(int number_of_paint) {
        this.number_of_paint = number_of_paint;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
