package com.hvktmm.at12i.model;

import javafx.beans.property.SimpleStringProperty;

public class ItemMember {

    private String id;

    private final SimpleStringProperty name;

    private final SimpleStringProperty address;

    private final SimpleStringProperty phone;

//    private final SimpleStringProperty status;

    public ItemMember(String id,String name, String add, String ph) {
        this.id = id;
        this.name =new SimpleStringProperty(name) ;
        this.address = new SimpleStringProperty(add);
        this.phone = new SimpleStringProperty(ph);
//        this.status = new SimpleStringProperty(st);

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

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

//    public String getStatus() {
//        return status.get();
//    }
//
//    public SimpleStringProperty statusProperty() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status.set(status);
//    }
}
