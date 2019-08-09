package com.hvktmm.at12i.service;

import com.hvktmm.at12i.dao.DepotDao;
import com.hvktmm.at12i.model.Depot;

import java.util.ArrayList;
import java.util.List;

public class DepotService {
    private DepotDao depotDao=new DepotDao();

    public List<Depot> check_amount(int amount,String id_paint){
        List<Depot> depot=new ArrayList<>();
//        List<Depot> list=depotDao.check_id_paint(id_paint);
//        int count=0;
//        for (Depot a:list){
//            if (count!=amount){
//                depot.add(a);
//                count+=a.getAmount();
//            }
//        }
        return depot;
    }


}
