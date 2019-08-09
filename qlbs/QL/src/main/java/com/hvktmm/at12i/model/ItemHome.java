package com.hvktmm.at12i.model;

import javafx.beans.property.SimpleStringProperty;

public class ItemHome {
    private String id;

    private final SimpleStringProperty tenmsp;

    private final SimpleStringProperty loaison;

    private final SimpleStringProperty size;

    private final SimpleStringProperty color;

    private final SimpleStringProperty soluong;

    private final SimpleStringProperty total;

    private final SimpleStringProperty status;


    public ItemHome(String id,String tenson, String loaison, String size, String color, String soluong, String total, String status){
        this.id=id;
        this.tenmsp=new SimpleStringProperty(tenson);
        this.loaison=new SimpleStringProperty(loaison);
        this.size=new SimpleStringProperty(size);
        this.color=new SimpleStringProperty(color);
        this.soluong=new SimpleStringProperty(soluong);
        this.total=new SimpleStringProperty(total);
        this.status=new SimpleStringProperty(status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenson() {
        return tenmsp.get();
    }

    public SimpleStringProperty tensonProperty() {
        return tenmsp;
    }

    public void setTenson(String tenson) {
        this.tenmsp.set(tenson);
    }

    public String getLoaison() {
        return loaison.get();
    }

    public SimpleStringProperty loaisonProperty() {
        return loaison;
    }

    public void setLoaison(String loaison) {
        this.loaison.set(loaison);
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getSoluong() {
        return soluong.get();
    }

    public SimpleStringProperty soluongProperty() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong.set(soluong);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
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
        return "ItemHome{" +
                "tenson=" + tenmsp +
                ", loaison=" + loaison +
                ", size=" + size +
                ", color=" + color +
                ", soluong=" + soluong +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
