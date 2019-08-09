package com.hvktmm.at12i.model;

import javafx.beans.property.SimpleStringProperty;

public class ItemPaint {
    private String id;

    private final SimpleStringProperty name;

    private final SimpleStringProperty type;

    private final SimpleStringProperty size;

    private final SimpleStringProperty color;

    private final SimpleStringProperty producer;

    private final SimpleStringProperty price;

    private  final SimpleStringProperty amount;

    private final SimpleStringProperty total;

    public ItemPaint(String id,String name, String type, String size, String color, String producer, String price) {
        this.id = id;
        this.name =new SimpleStringProperty(name) ;
        this.type = new SimpleStringProperty(type);
        this.size = new SimpleStringProperty(size);
        this.color = new SimpleStringProperty(color);
        this.producer = new SimpleStringProperty(producer);
        this.price =new SimpleStringProperty(price);
        this.total=null;
        this.amount=null;
    }
    public ItemPaint(String id,String name, String type, String size, String color,String amount, Float total) {

        this.id = id;
        this.name =new SimpleStringProperty(name) ;
        this.type = new SimpleStringProperty(type);
        this.size = new SimpleStringProperty(size);
        this.color = new SimpleStringProperty(color);
//        this.producer = new SimpleStringProperty(producer);
//        this.price =new SimpleStringProperty(price);
        this.amount=new SimpleStringProperty(amount);
        this.total=new SimpleStringProperty(String.valueOf(total));
        this.producer = null;
        this.price = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public String getProducer() {
        return producer.get();
    }

    public SimpleStringProperty producerProperty() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer.set(producer);
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

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
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
}
