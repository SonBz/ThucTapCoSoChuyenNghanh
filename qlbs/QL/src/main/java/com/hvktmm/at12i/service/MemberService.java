package com.hvktmm.at12i.service;

import com.hvktmm.at12i.dao.MemberDao;
import com.hvktmm.at12i.model.ItemMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemberService {

    private final ObservableList<String> data=FXCollections.observableArrayList();

    private MemberDao memberDao=null;
    //    public ObservableList<String> getData(){
//        return data;
//    }
    public ObservableList<String> Paint_List(String status) {
        memberDao=new MemberDao();
        for (ItemMember a : memberDao.list_member(status)) {
            data.addAll(a.getName());
        }
        return data;
    }

}
