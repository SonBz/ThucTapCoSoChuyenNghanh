package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.dao.*;
import com.hvktmm.at12i.model.*;
import com.hvktmm.at12i.service.MemberService;
import com.hvktmm.at12i.service.PaintService;
import com.hvktmm.at12i.utils.DateUtil;
import com.hvktmm.at12i.utils.StatusUtil;
import com.itextpdf.text.DocumentException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ImportDepotController implements Initializable {
    public Label lbname;
    public Label lbdate;
//    public TextField txtname;
//    public TextField txtaddress;
//    public TextField txtphone;
    public TextField txtsl;
    public Label lbdg;
    public Button btnnhap;
    public Label lbtype;
    public Label lbsize;
    public Label lbmau;
    public Button btnadd;
    public TableView view_table;
    public ComboBox cbson;
    public TableColumn total;
    public Label lbcode_id;
    public ComboBox cbname;
    public Label lbadd;
    public Label lbphone;
    public Button btnaddncc;
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

    private ObservableList<ItemPaint> PaintData = FXCollections.observableArrayList();



    String id_paint="";

    float tong;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbname.setText(LoginController.getVariable().getName());
        lbdate.setText(DateUtil.getCurrtentTime());
        DepotDao depotDao=new DepotDao();
//        Depot depot=new Depot();
        Depot depot=depotDao.id_code();
        lbcode_id.setText(String.valueOf(depot.getCode_id()+1));

        MemberService memberService=new MemberService();
        ObservableList<String> ncc=memberService.Paint_List(StatusUtil.producer);
        cbname.setItems(ncc);

        PaintService data=new PaintService();
        ObservableList<String> tab=data.Paint_List();
        cbson.setItems(tab);

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
        PropertyValueFactory<ItemHome,String> tenp=new PropertyValueFactory<>("name");
        PropertyValueFactory<ItemHome,String> loaisonp=new PropertyValueFactory<>("type");
        PropertyValueFactory<ItemHome,String> sizep=new PropertyValueFactory<>("size");
        PropertyValueFactory<ItemHome,String> colorp=new PropertyValueFactory<>("color");
        PropertyValueFactory<ItemHome,String> soluongp=new PropertyValueFactory<>("amount");
        PropertyValueFactory<ItemHome,String> tongtienp=new PropertyValueFactory<>("total");
        tenson.setCellValueFactory(tenp);
        loaison.setCellValueFactory(loaisonp);
        size.setCellValueFactory(sizep);
        color.setCellValueFactory(colorp);
        soluong.setCellValueFactory(soluongp);
        total.setCellValueFactory(tongtienp);

        view_table.setItems(PaintData);
    }

    public void displaySelected(ActionEvent event) {
        String item=cbson.getSelectionModel().getSelectedItem().toString();
        PaintDao paintDao=new PaintDao();
        List<ItemPaint> list=paintDao.table_list();

        for (ItemPaint a:list){
            if (a.getName().equals(item)){
                lbtype.setText(a.getType());
                lbsize.setText(a.getSize());
                lbdg.setText(String.valueOf(a.getPrice()));
                lbmau.setText(a.getColor());
                id_paint=a.getId();
            }
        }
    }

    public void nhap(ActionEvent event) {
        String name= (String) cbson.getValue();
        String type=lbtype.getText();
        String size=lbsize.getText();
        String color=lbmau.getText();
        String price=lbdg.getText();
        String amount=txtsl.getText();
        Float total=Float.valueOf(amount)*Float.valueOf(price);
        ItemPaint item=new ItemPaint(id_paint,name,type,size,color,amount,total);
        PaintData.add(item);

    }

    public void nhapkho(ActionEvent event) throws IOException, DocumentException {
        OderDao oderDao=new OderDao();
        DepotDao depotDao=new DepotDao();
        MemberDao memberDao=new MemberDao();

        //viet check truong hop (so luong son(int,0),sdt)
        ArrayList<Depot> depots_list=new ArrayList<>();
        for (int i=0;i<PaintData.size();i++){
            Depot depot1=depotDao.id_code();
            int id=depot1.getId()+1;
            Depot depot=new Depot(id,Integer.valueOf(PaintData.get(i).getId()),PaintData.get(i).getName(),DateUtil.getCurrtentTime(),Integer.valueOf(PaintData.get(i).getAmount()),StatusUtil.conhang);
            depotDao.insertdepot(depot,LoginController.getVariable().getId(),Integer.valueOf(lbcode_id.getText()));
//            System.out.println("them kho thanh cong");
            tong+=Float.valueOf(PaintData.get(i).getTotal());
//            lbcode_id.setText(String.valueOf(depotDao.id_code()));
//            PaintData.removeAll(Collections.singleton(true));
            depots_list.add(depot);
//
        }
        Member member=memberDao.check_member(lbphone.getText(),StatusUtil.producer);
        if (member==null){
            member=new Member();
            String name=(String) cbname.getValue();
            String address=lbadd.getText();
            String phone=lbphone.getText();
            member.setId(memberDao.id_code()+1);
            member.setName(name);
            member.setAddress(address);
            member.setPhone(phone);
            member.setStatus_member(StatusUtil.producer);
            memberDao.insert(member);
        }
        oderDao.insert(member.getId(),Integer.valueOf(lbcode_id.getText()),LoginController.getVariable().getId(),tong,Integer.valueOf(lbcode_id.getText()));

        for (int i=0;i<PaintData.size();i++){
            History_buyDao history_buy=new History_buyDao();
            for (Depot a:depots_list){
                if (Integer.valueOf(PaintData.get(i).getId())==a.getId_paint()){
                    history_buy.insert_history_buy(member.getId(),Integer.valueOf(lbcode_id.getText()),a.getId(),a.getAmount(),Float.valueOf(PaintData.get(i).getTotal()),Integer.valueOf(lbcode_id.getText()));
                }
            }
        }
        ReportsController.createPDF_nhapkho(member,PaintData,lbcode_id.getText(),String.valueOf(tong));
    }

    public void themmoi(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/Scene/themson1.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void addncc(ActionEvent event) throws Exception  {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/Scene/themNCC.fxml"));
        Parent root=fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();


    }

    public void viewncc(ActionEvent event) {
        String item=cbname.getSelectionModel().getSelectedItem().toString();
        MemberDao memberDao=new MemberDao();
        List<ItemMember> list=memberDao.list_member(StatusUtil.producer);

        for (ItemMember a:list){
            if (a.getName().equals(item)){
                lbadd.setText(a.getAddress());
                lbphone.setText(a.getPhone());

            }
        }
    }
}
