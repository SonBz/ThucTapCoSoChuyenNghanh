package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.model.Details;
import com.hvktmm.at12i.dao.HomeDao;
import com.hvktmm.at12i.model.ItemHome;
import com.hvktmm.at12i.service.HomeService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Label lbson;
    public Label lbuser;
//    public Label lbsp;
    public Label lbtype;
    public Label lbnamesp;
    public Label lbsize;
    public Label lbcolor;
    public Label lbprice;
    public Label lbdate_in;
    public Label lbdate_out;
    public Label lbcount;
    public Label lbsum;
    public Label lbnsx;
    public Label lbstatus;
    @FXML
    private TableView list_view_table;
    @FXML
    private TableColumn stt;
    @FXML
    private TableColumn tenson;
    @FXML
    private TableColumn loaison;
    @FXML
    private TableColumn size;
    @FXML
    private TableColumn color;
    @FXML
    private TableColumn soluong;
//    @FXML
//    private TableColumn tongtien;
    @FXML
    private TableColumn status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stt.setCellFactory(col -> {
            TableCell<String, Integer> indexCell = new TableCell<>();
            ReadOnlyObjectProperty<TableRow> rowProperty = indexCell.tableRowProperty();
            ObjectBinding<String> rowBinding = Bindings.createObjectBinding(() -> {
                TableRow<String> row = rowProperty.get();
                if (row != null) {
                    int rowIndex = row.getIndex()+1;
                    if (rowIndex <= row.getTableView().getItems().size()) {
                        return Integer.toString(rowIndex);
                    }
                }
                return null;
            }, rowProperty);
            indexCell.textProperty().bind(rowBinding);
            return indexCell;
        });
        PropertyValueFactory<ItemHome,String> sttp=new PropertyValueFactory<>("tenson");
        PropertyValueFactory<ItemHome,String> tenp=new PropertyValueFactory<>("tenson");
        PropertyValueFactory<ItemHome,String> loaisonp=new PropertyValueFactory<>("loaison");
        PropertyValueFactory<ItemHome,String> sizep=new PropertyValueFactory<>("size");
        PropertyValueFactory<ItemHome,String> colorp=new PropertyValueFactory<>("color");
        PropertyValueFactory<ItemHome,String> soluongp=new PropertyValueFactory<>("soluong");
        PropertyValueFactory<ItemHome,String> tongtienp=new PropertyValueFactory<>("total");
        PropertyValueFactory<ItemHome,String> statusp=new PropertyValueFactory<>("status");
        stt.setCellValueFactory(sttp);
        tenson.setCellValueFactory(tenp);
        loaison.setCellValueFactory(loaisonp);
        size.setCellValueFactory(sizep);
        color.setCellValueFactory(colorp);
        soluong.setCellValueFactory(soluongp);
//        tongtien.setCellValueFactory(tongtienp);
        status.setCellValueFactory(statusp);
        HomeService data=new HomeService();
        ObservableList<ItemHome> tab=data.getData();
        list_view_table.setItems(tab);
    }

    @FXML
    public void displaySelected(javafx.scene.input.MouseEvent mouseEvent) {
        ItemHome itemHome= (ItemHome) list_view_table.getSelectionModel().getSelectedItem();
//        System.out.println(itemHome.getId());
        HomeDao a=new HomeDao();
        Details ab= a.details(itemHome.getId());
        lbuser.setText(ab.getUser());
        lbnamesp.setText(itemHome.getTenson());
        lbson.setText(ab.getNamepaint());
        lbtype.setText(itemHome.getLoaison());
        lbnsx.setText(ab.getProduce());
        lbsize.setText(itemHome.getSoluong());
        lbcolor.setText(itemHome.getColor());
        lbcount.setText(itemHome.getSoluong());
        lbsum.setText(itemHome.getTotal());
        lbdate_in.setText(ab.getDate_in());
        lbdate_out.setText(ab.getDate_out());
        lbstatus.setText(itemHome.getStatus());
        lbprice.setText(ab.getPrice());
    }

    public void timkiem(ActionEvent event) {
    }
}
