package com.hvktmm.at12i.service;

import com.hvktmm.at12i.dao.PaintDao;
import com.hvktmm.at12i.model.ItemPaint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PaintService {
    private final ObservableList<String> data=FXCollections.observableArrayList();

    private PaintDao paintDao=null;
//    public ObservableList<String> getData(){
//        return data;
//    }
    public ObservableList<String> Paint_List() {
        paintDao=new PaintDao();
        for (ItemPaint a : paintDao.table_list()) {
            data.addAll(a.getName());
        }
        return data;
    }


//    public ObservableList<Item_Statistical> Xuatkho(String bd,String kt) throws ParseException {
//        statisticalDao=new StatisticalDao();
//        for (Item_Statistical a :statisticalDao.table_list()) {
//            Long in=DateUtil.getCurrentTime1(bd);
//            Long out=DateUtil.getCurrentTime1(kt);
//            Long check=DateUtil.getCurrentTime1(a.getDate_in());
//            if (in<=check && out>=check){
//                data.addAll(a);
//            }
//        }
//        return data;
//    }
}
