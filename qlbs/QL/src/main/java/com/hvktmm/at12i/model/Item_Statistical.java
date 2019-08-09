package com.hvktmm.at12i.model;

import javafx.beans.property.SimpleStringProperty;

public class Item_Statistical {
    private final SimpleStringProperty code_id;

    private final SimpleStringProperty name_user;

    private final SimpleStringProperty date_in;

    private final SimpleStringProperty status;

    private final SimpleStringProperty number_of_paint;

    private final SimpleStringProperty price;

    public Item_Statistical(String code_id, String name_user, String date_in,String status, String number_of_paint, String price) {
        this.code_id = new SimpleStringProperty(code_id);
        this.name_user = new SimpleStringProperty(name_user);
        this.date_in = new SimpleStringProperty(date_in);
        this.status=new SimpleStringProperty(status);
        this.number_of_paint = new SimpleStringProperty(number_of_paint);
        this.price = new SimpleStringProperty(price);
    }
    public Item_Statistical( String name_user,String namekh, String date_in, String number_of_paint, String price) {
        this.code_id = null;
        this.name_user = new SimpleStringProperty(name_user);
        this.date_in = new SimpleStringProperty(date_in);
        this.status=new SimpleStringProperty(namekh);
        this.number_of_paint = new SimpleStringProperty(number_of_paint);
        this.price = new SimpleStringProperty(price);
    }

    public Item_Statistical( String name,String number_of_paint, String price,String date_in) {
        this.code_id = null;
        this.name_user = new SimpleStringProperty(name);
        this.date_in = new SimpleStringProperty(date_in);
        this.status=null;
        this.number_of_paint = new SimpleStringProperty(number_of_paint);
        this.price = new SimpleStringProperty(price);
    }

    public String getCode_id() {
        return code_id.get();
    }

    public SimpleStringProperty code_idProperty() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id.set(code_id);
    }

    public String getName_user() {
        return name_user.get();
    }

    public SimpleStringProperty name_userProperty() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user.set(name_user);
    }

    public String getDate_in() {
        return date_in.get();
    }

    public SimpleStringProperty date_inProperty() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in.set(date_in);
    }

    public String getNumber_of_paint() {
        return number_of_paint.get();
    }

    public SimpleStringProperty number_of_paintProperty() {
        return number_of_paint;
    }

    public void setNumber_of_paint(String number_of_paint) {
        this.number_of_paint.set(number_of_paint);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    @Override
    public String toString() {
        return "Item_Statistical{" +
                "code_id=" + code_id +
                ", name_user=" + name_user +
                ", date_in=" + date_in +
                ", status=" + status +
                ", number_of_paint=" + number_of_paint +
                ", price=" + price +
                '}';
    }
}
