package com.hvktmm.at12i.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    public JFXButton btnkho;
    @FXML
    public JFXButton btnnhap;
    @FXML
    public JFXButton btnxuat;
    @FXML
    public JFXButton btnthongke;
    @FXML
    public JFXButton btnsetting;
    @FXML
    private JFXButton btncheck;

    public void dashboard(ActionEvent event) throws IOException {

        Stage stage;
        Parent parent;
        stage= (Stage) btncheck.getScene().getWindow();
        parent=FXMLLoader.load(getClass().getResource("test.fxml"));
        Scene scene=new Scene(parent);
//            boolean fullscreen=stage.isFullScreen();
//            stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public void xuatkho(ActionEvent event) throws IOException {
        redict(btnxuat,"/Scene/xuat.fxml");
    }

    public void thongke(ActionEvent event) throws IOException{
        redict(btnthongke,"/Scene/thongke.fxml");
    }

    public void setting(ActionEvent event) {
    }

    public void nhapkho(ActionEvent event) throws IOException {
        redict(btnnhap,"/Scene/nhapkho.fxml");
    }
    public void kho(ActionEvent event) throws IOException {
        redict(btnkho,"/Scene/home.fxml");
    }

    public void redict(JFXButton btn,String fxml) throws IOException{
        Stage stage;
        Parent parent;
        stage= (Stage) btn.getScene().getWindow();
        parent=FXMLLoader.load(getClass().getResource(fxml));
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
