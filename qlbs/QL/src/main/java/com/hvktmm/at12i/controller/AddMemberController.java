package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.dao.MemberDao;
import com.hvktmm.at12i.model.Member;
import com.hvktmm.at12i.utils.StatusUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMemberController {
    public TextField lbnamecc;
    public TextField lbaddress;
    public TextField lbphone;

    public void them_ncc(ActionEvent event) throws IOException {
        Member member=new Member();
        MemberDao memberDao=new MemberDao();
        member.setId(memberDao.id_code()+1);
        member.setName(lbnamecc.getText());
        member.setPhone(lbphone.getText());
        member.setAddress(lbaddress.getText());
        member.setStatus_member(StatusUtil.producer);
        try {
            memberDao.insert(member);
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        Stage stage=new Stage();
//        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/Scene/nhapkho.fxml"));
//        Parent root1 = fxmlLoader1.load();
//        stage.getScene().setRoot(root1);

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
