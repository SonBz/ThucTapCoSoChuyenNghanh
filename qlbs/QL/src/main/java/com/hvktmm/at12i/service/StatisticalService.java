package com.hvktmm.at12i.service;

import com.hvktmm.at12i.controller.Statistical;
import com.hvktmm.at12i.dao.StatisticalDao;
import com.hvktmm.at12i.model.ItemPaint;
import com.hvktmm.at12i.model.Item_Statistical;
import com.hvktmm.at12i.utils.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.util.List;

public class StatisticalService {

    private  ObservableList<Item_Statistical> data=FXCollections.observableArrayList();

    private  ObservableList<Item_Statistical> data1=FXCollections.observableArrayList();

    private  ObservableList<Item_Statistical> nv=FXCollections.observableArrayList();

    private  ObservableList<Item_Statistical> kh=FXCollections.observableArrayList();

    private StatisticalDao statisticalDao=null;
//    public ObservableList<Item_Statistical> getData(){
//        return data;
//    }
    public ObservableList<Item_Statistical> Xuatkho(String bd,String kt) throws ParseException {
        statisticalDao=new StatisticalDao();
        for (Item_Statistical a :statisticalDao.table_list()) {
            Long in=DateUtil.getCurrentTime1(bd);
            Long out=DateUtil.getCurrentTime1(kt);
            Long check=DateUtil.getCurrentTime1(a.getDate_in());
            if (in<=check && out>=check){
                data.addAll(a);
            }
        }
        return data;
    }
    public float total(){
        float total = 0;
        for (Item_Statistical a :data) {
                total+=Float.valueOf(a.getPrice());
        }
        return total;
    }

    public ObservableList<Item_Statistical> tk_xuatkho(String bd,String kt) throws ParseException {
        statisticalDao=new StatisticalDao();
        for (Item_Statistical a :statisticalDao.tk_xuatkho()) {
            Long in=DateUtil.getCurrentTime1(bd);
            Long out=DateUtil.getCurrentTime1(kt);
            Long check=DateUtil.getCurrentTime2(a.getDate_in());
            if (in<=check && out>=check){
                data1.addAll(a);
            }
        }
        return data1;
    }

    public float total_xuat(){
        float total = 0;
        for (Item_Statistical a :data1) {
            total+=Float.valueOf(a.getPrice());
        }
        return total;
    }

    public ObservableList<Item_Statistical> tk_nhanvien(String bd,String kt,String status) throws ParseException {
        statisticalDao=new StatisticalDao();
        for (Item_Statistical a :statisticalDao.tk_nv(status)) {
            Long in=DateUtil.getCurrentTime1(bd);
            Long out=DateUtil.getCurrentTime1(kt);
            Long check=DateUtil.getCurrentTime2(a.getDate_in());
            if (in<=check && out>=check){
                nv.addAll(a);
            }
        }
        return nv;
    }

    public ObservableList<Item_Statistical> tk_kh(String bd,String kt,String status) throws ParseException {
        statisticalDao=new StatisticalDao();
        for (Item_Statistical a :statisticalDao.tk_kh(status)) {
            Long in=DateUtil.getCurrentTime1(bd);
            Long out=DateUtil.getCurrentTime1(kt);
            Long check=DateUtil.getCurrentTime2(a.getDate_in());
            if (in<=check && out>=check){
                kh.addAll(a);
            }
        }
        return kh;
    }

//    public static void main(String[] args) throws ParseException {
//        StatisticalService a=new StatisticalService();
//        List<Item_Statistical> b=a.tk_nhanvien("2018-10-1","2018-10-22","0");
//        for (Item_Statistical c:b){
//            System.out.println(c.toString());
//        }
//        System.out.println(b.size());
//    }
}
