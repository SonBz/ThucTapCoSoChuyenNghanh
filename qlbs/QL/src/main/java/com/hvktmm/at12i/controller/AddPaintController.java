package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.dao.PaintDao;
import com.hvktmm.at12i.model.Paint;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddPaintController {
    public TextField txtncc;
    public TextField txtname;
    public TextField txttype;
    public TextField txtcolor;
    public TextField txtsize;
    public TextField txtprice;

    public void them(ActionEvent event) throws IOException {
        Paint paint=new Paint();
        PaintDao paintDao=new PaintDao();
        paint.setId(paintDao.id_code()+1);
        paint.setName(txtname.getText());
        paint.setProducer(txtncc.getText());
        paint.setType(txttype.getText());
        paint.setSize(txtsize.getText());
        paint.setColor(txtcolor.getText());
        paint.setPrice(Float.valueOf(txtprice.getText()));
        try {
            paintDao.insertpaint(paint);
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
