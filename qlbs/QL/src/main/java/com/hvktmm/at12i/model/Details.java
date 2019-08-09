package com.hvktmm.at12i.model;

public class Details {
    private String user;

    private String namepaint;

    private String date_in;

    private String date_out;

    private String produce;

    private String price;

    public String getUser() {
        return user;
    }

    public String getNamepaint() {
        return namepaint;
    }

    public void setNamepaint(String namepaint) {
        this.namepaint = namepaint;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate_in() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public String getDate_out() {
        return date_out;
    }

    public void setDate_out(String date_out) {
        this.date_out = date_out;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Details{" +
                "user='" + user + '\'' +
                ", namepaint='" + namepaint + '\'' +
                ", date_in='" + date_in + '\'' +
                ", date_out='" + date_out + '\'' +
                ", produce='" + produce + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
