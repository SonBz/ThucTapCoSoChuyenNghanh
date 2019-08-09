package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.dao.UserDao;
import com.hvktmm.at12i.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private Button btnlogin;

    @FXML
    private TextField txtuser;

    @FXML
    private TextField txtpass;

    private UserDao userDao=new UserDao();

    static User user2;
    public void clicklogin(ActionEvent event) throws Exception{
        String user=txtuser.getText();
        String pass=txtpass.getText();
        User user1=userDao.login(user,pass);
        setVariable(user1);
        if (user1!=null){
            Stage stage;
            Parent parent;
            stage= (Stage) btnlogin.getScene().getWindow();
            parent=FXMLLoader.load(getClass().getResource("/Scene/home.fxml"));
            Scene scene=new Scene(parent);
            boolean fullscreen=stage.isFullScreen();
//            stage.setFullScreen(true);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Đăng Nhập không thành công");
            alert.setContentText("Sai tên tài khoản hoặc mật khẩu");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public static User getVariable(){
        return user2;
    }
    public static void setVariable(User user){
        user2=user;
    }
}
