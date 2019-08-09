package com.hvktmm.at12i.model;

public class Member {
    private int id;

//    private int id_depot;

    private String name;

    private String address;

    private String phone;

    private String status_member;
//    private String day_trading;
//
//    private float price;
//
//    private String status_price;

    public Member(){
    }

    public Member(String name, String address, String phone, String status_member) {
//        this.id_depot = id_depot;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status_member = status_member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getId_depot() {
//        return id_depot;
//    }
//
//    public void setId_depot(int id_depot) {
//        this.id_depot = id_depot;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus_member() {
        return status_member;
    }

    public void setStatus_member(String status_member) {
        this.status_member = status_member;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", status_member='" + status_member + '\'' +
                '}';
    }
}
