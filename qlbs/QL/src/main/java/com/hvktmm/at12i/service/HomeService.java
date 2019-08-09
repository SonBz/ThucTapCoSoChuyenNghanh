package com.hvktmm.at12i.service;

import com.hvktmm.at12i.dao.HomeDao;
import com.hvktmm.at12i.model.ItemHome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HomeService {
    private final ObservableList<ItemHome> data=FXCollections.observableArrayList();

    private HomeDao homeDao=new HomeDao();
    public ObservableList<ItemHome> getData(){
        return data;
    }
    public HomeService(){
        for (ItemHome a :homeDao.table_list()){
            data.addAll(a);
        }
//        data.addAll(homeDao.table_list());
    }

//    public static void main(String[] args) {
////        HomeService a=new HomeService();
//        for (ItemHome s : new HomeService()){
//
//        }
//    }
}
