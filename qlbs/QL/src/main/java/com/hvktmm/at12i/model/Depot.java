package com.hvktmm.at12i.model;

public class Depot {
    private int id;

    private int id_paint;

    private String name;

    private String date_in;

    private String date_out;

    private int amount;

    private String status;
    private int code_id;

    public Depot() {
    }

    public Depot(int id, int id_paint, String name, String date_in, int amount, String status) {
        this.id = id;
        this.id_paint = id_paint;
        this.name = name;
        this.date_in = date_in;
//        this.date_out = date_out;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paint() {
        return id_paint;
    }

    public void setId_paint(int id_paint) {
        this.id_paint = id_paint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode_id() {
        return code_id;
    }

    public void setCode_id(int code_id) {
        this.code_id = code_id;
    }
}
