package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.model.Item_Statistical;
import com.hvktmm.at12i.service.StatisticalService;
import com.hvktmm.at12i.utils.StatusUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class Statistical implements Initializable {
    public DatePicker date_in;
    public DatePicker date_out;
    public TableView list_table;
    public TableColumn stt;
    public TableColumn code_id;
    public TableColumn clname;
    public TableColumn lbdate;
    public TableColumn lb_count;
    public TableColumn lb_tong;
    public Label total;
    public TableColumn lbstatus;

    public TableColumn lbkh;
    public TableColumn lbdate_buy;
    public TableColumn clname1;
    public TableColumn stt1;
    public TableColumn lb_count1;
    public TableColumn lb_tong1;
    public TableView list_table1;
    public Label total1;
    public DatePicker date_in1;
    public DatePicker date_out1;
    public Button btnbaocao1;


    public DatePicker date_in11;
    public DatePicker date_out11;
    public TableView list_tablenv;
    public TableColumn lb_tong1nv;
    public TableColumn lb_countnv;
    public TableColumn clnamenv;
    public TableColumn sttnv;
    public RadioButton rdnhap;
    public RadioButton rdban;
    
    
    public TableColumn sttkh;
    public TableColumn clnamekh;
    public TableColumn lb_countkh;
    public TableColumn lb_tongkh;
    public TableView list_tablekh;
    public DatePicker date_inkh;
    public DatePicker date_outkh;
    public RadioButton rdkh;
    public RadioButton rdncckh;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void xem(ActionEvent event) throws ParseException {

        String bd= String.valueOf(date_in.getValue());
        String kt= String.valueOf(date_out.getValue());
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
        PropertyValueFactory<Item_Statistical,String> sttp=new PropertyValueFactory<>("code_id");
        PropertyValueFactory<Item_Statistical,String> cde=new PropertyValueFactory<>("code_id");
        PropertyValueFactory<Item_Statistical,String> nme=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> ng=new PropertyValueFactory<>("date_in");
        PropertyValueFactory<Item_Statistical,String> st=new PropertyValueFactory<>("status");
        PropertyValueFactory<Item_Statistical,String> cou=new PropertyValueFactory<>("number_of_paint");
        PropertyValueFactory<Item_Statistical,String> ga=new PropertyValueFactory<>("price");

        stt.setCellValueFactory(sttp);
        code_id.setCellValueFactory(cde);
        clname.setCellValueFactory(nme);
        lbdate.setCellValueFactory(ng);
        lbstatus.setCellValueFactory(st);
        lb_count.setCellValueFactory(cou);
        lb_tong.setCellValueFactory(ga);
        StatisticalService data=new StatisticalService();
        ObservableList<Item_Statistical> tab=data.Xuatkho(bd,kt);
        list_table.setItems(tab);

        total.setText(String.valueOf(data.total()));
    }

    public void Pdfxuatkho(ActionEvent event) {
    }

    public void xem1(ActionEvent event) throws ParseException {

        String bd= String.valueOf(date_in1.getValue());
        String kt= String.valueOf(date_out1.getValue());
        stt1.setCellFactory(col -> {
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
        PropertyValueFactory<Item_Statistical,String> sttp=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> cde=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> nme=new PropertyValueFactory<>("status"); //kh
        PropertyValueFactory<Item_Statistical,String> ng=new PropertyValueFactory<>("date_in");

        PropertyValueFactory<Item_Statistical,String> cou=new PropertyValueFactory<>("number_of_paint");
        PropertyValueFactory<Item_Statistical,String> ga=new PropertyValueFactory<>("price");

        stt1.setCellValueFactory(sttp);
        clname1.setCellValueFactory(cde);
        lbkh.setCellValueFactory(nme);
        lbdate_buy.setCellValueFactory(ng);
//        lbstatus.setCellValueFactory(st);
        lb_count1.setCellValueFactory(cou);
        lb_tong1.setCellValueFactory(ga);
        StatisticalService data=new StatisticalService();
        ObservableList<Item_Statistical> tab=data.tk_xuatkho(bd,kt);
        list_table1.setItems(tab);

        total1.setText(String.valueOf(data.total_xuat()));
    }

    public void xemnv(ActionEvent event) throws ParseException {
        String bd= String.valueOf(date_in11.getValue());
        String kt= String.valueOf(date_out11.getValue());
        sttnv.setCellFactory(col -> {
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
        PropertyValueFactory<Item_Statistical,String> sttp=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> cde=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> cou=new PropertyValueFactory<>("number_of_paint");
        PropertyValueFactory<Item_Statistical,String> ga=new PropertyValueFactory<>("price");

        sttnv.setCellValueFactory(sttp);
        clnamenv.setCellValueFactory(cde);
        lb_countnv.setCellValueFactory(cou);
        lb_tong1nv.setCellValueFactory(ga);
        StatisticalService data=new StatisticalService();
        String status="";
        if (rdban.isSelected()){
            status=StatusUtil.producer;
        }
        else if (rdnhap.isSelected()){
            status=StatusUtil.customer;
        }
        System.out.println(status);
        ObservableList<Item_Statistical> tab=data.tk_nhanvien(bd,kt,status);
        list_tablenv.setItems(tab);


    }

    public void xemkh(ActionEvent event) throws ParseException {
        String bd= String.valueOf(date_inkh.getValue());
        String kt= String.valueOf(date_outkh.getValue());
        sttkh.setCellFactory(col -> {
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
        PropertyValueFactory<Item_Statistical,String> sttp=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> cde=new PropertyValueFactory<>("name_user");
        PropertyValueFactory<Item_Statistical,String> cou=new PropertyValueFactory<>("number_of_paint");
        PropertyValueFactory<Item_Statistical,String> ga=new PropertyValueFactory<>("price");

        sttkh.setCellValueFactory(sttp);
        clnamekh.setCellValueFactory(cde);
        lb_countkh.setCellValueFactory(cou);
        lb_tongkh.setCellValueFactory(ga);
        StatisticalService data=new StatisticalService();
        String status="";
        if (rdncckh.isSelected()){
            status=StatusUtil.producer;
        }
        else if (rdkh.isSelected()){
            status=StatusUtil.customer;
        }
        System.out.println(status);
        ObservableList<Item_Statistical> tab=data.tk_kh(bd,kt,status);
        list_tablekh.setItems(tab);
    }
}
