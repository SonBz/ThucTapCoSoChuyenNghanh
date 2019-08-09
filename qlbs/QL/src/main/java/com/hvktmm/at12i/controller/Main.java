package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.service.HomeService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/Scene/login.fxml"));
        primaryStage.setTitle("Quản Lý Bán Sơn");
        primaryStage.setScene(new Scene(root, 350, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        HomeService a=new HomeService();
        System.out.println(a.getData().size());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
