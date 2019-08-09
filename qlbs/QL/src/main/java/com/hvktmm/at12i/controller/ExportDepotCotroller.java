package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.dao.*;
import com.hvktmm.at12i.model.Depot;
import com.hvktmm.at12i.model.ItemPaint;
import com.hvktmm.at12i.model.Member;
import com.hvktmm.at12i.service.DepotService;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ExportDepotCotroller implements Initializable {
    public Label lbdate;
    public Label lbuser;
    public TextField txtname;
    public TextField txtphone;
    public TextField txtaddress;
    public ComboBox cbson;
    public Label lbcolor;
    public Label lbtype;
    public Label lbsize;
    public Label lbdg;
    public TextField txtsl;
    public Button btnadd;
    public TableView list_table;
    public TableColumn tenson;
    public TableColumn loaison;
    public TableColumn kichthuoc;
    public TableColumn mau;
    public TableColumn sl;
    public TableColumn total;
    public TableColumn stt;
    public Label id_code;

    String id_paint="";

    private ObservableList<ItemPaint> PaintData = FXCollections.observableArrayList();

    float tong ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbuser.setText(LoginController.getVariable().getName());
        lbdate.setText(DateUtil.getCurrtentTime());

        PaintService data=new PaintService();
        ObservableList<String> tab=data.Paint_List();
        cbson.setItems(tab);

        OderDao oderDao=new OderDao();
        id_code.setText(String.valueOf(oderDao.id_code()+1));

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
        PropertyValueFactory<ItemPaint,String> tenp=new PropertyValueFactory<>("name");
        PropertyValueFactory<ItemPaint,String> loaisonp=new PropertyValueFactory<>("type");
        PropertyValueFactory<ItemPaint,String> sizep=new PropertyValueFactory<>("size");
        PropertyValueFactory<ItemPaint,String> colorp=new PropertyValueFactory<>("color");
        PropertyValueFactory<ItemPaint,String> soluongp=new PropertyValueFactory<>("amount");
        PropertyValueFactory<ItemPaint,String> tongtienp=new PropertyValueFactory<>("total");
        tenson.setCellValueFactory(tenp);
        loaison.setCellValueFactory(loaisonp);
        kichthuoc.setCellValueFactory(sizep);
        mau.setCellValueFactory(colorp);
        sl.setCellValueFactory(soluongp);
        total.setCellValueFactory(tongtienp);

        list_table.setItems(PaintData);

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
                lbcolor.setText(a.getColor());
                id_paint=a.getId();
            }
        }
    }

    public void them(ActionEvent event) {
        String name= (String) cbson.getValue();
        String type=lbtype.getText();
        String size=lbsize.getText();
        String color=lbcolor.getText();
        String price=lbdg.getText();
        String amount=txtsl.getText();
        Float total=Float.valueOf(amount)*Float.valueOf(price);
        ItemPaint item=new ItemPaint(id_paint,name,type,size,color,amount,total);
        PaintData.add(item);

    }

    public void xuatkho(ActionEvent event) throws IOException, DocumentException {

        OderDao oderDao=new OderDao();
        DepotDao depotDao=new DepotDao();
        MemberDao memberDao=new MemberDao();
//        DepotService depotService=new DepotService();
        Random random=new Random();
        int id_oder=random.nextInt(10000);
        History_buyDao history_buyDao=new History_buyDao();
        //viet check truong hop (so luong son(int,0),sdt)

        Member member=memberDao.check_member(txtphone.getText(),StatusUtil.customer);
        if (member==null){
            member=new Member();
            String name=txtname.getText();
            String address=txtaddress.getText();
            String phone=txtphone.getText();
            member.setId(memberDao.id_code()+1);
            member.setName(name);
            member.setAddress(address);
            member.setPhone(phone);
            member.setStatus_member(StatusUtil.customer);
            memberDao.insert(member);
        }

        for (int c=0;c<PaintData.size();c++){
            tong+=Float.valueOf(PaintData.get(c).getTotal());
        }
        oderDao.insert(member.getId(),Integer.valueOf(id_code.getText()),LoginController.getVariable().getId(),tong,id_oder);
        float total;
        for (int i=0;i<PaintData.size();i++) {
            int count = Integer.parseInt(PaintData.get(i).getAmount());
            List<Depot> list = depotDao.check_id_paint(PaintData.get(i).getId()); //sua
            for (int j = 0; j < list.size(); j++) {
                if (count == list.get(j).getAmount()) {
//                    System.out.println("TH1");
                    depotDao.update(list.get(j).getId(), 0, StatusUtil.hethang);
                    history_buyDao.insert_history_buy(member.getId(), id_oder, list.get(j).getId(), count, Float.valueOf(PaintData.get(i).getTotal()), list.get(j).getCode_id());
                    break;
                } else if (count > list.get(j).getAmount() && list.get(j).getAmount() != 0) { //count lon hon
//                    System.out.println("TH2");
                    total = Float.valueOf(count - list.get(j).getAmount()) * Float.valueOf(PaintData.get(i).getAmount());
                    count -= list.get(j).getAmount();
                    depotDao.update(list.get(j).getId(), 0, StatusUtil.hethang);
                    history_buyDao.insert_history_buy(member.getId(), id_oder, list.get(j).getId(), list.get(j).getAmount(), total, list.get(j).getCode_id());
                } else if (count < list.get(j).getAmount()) {
//                    System.out.println("Th3");
                    int number = list.get(j).getAmount() - count;
                    depotDao.update(list.get(j).getId(), number, StatusUtil.conhang);
//                    total = Float.valueOf(count) * Float.valueOf(PaintData.get(i).getPrice());
                    total = Float.valueOf(PaintData.get(i).getTotal());
                    history_buyDao.insert_history_buy(member.getId(), id_oder, list.get(j).getId(), count, total, list.get(j).getCode_id());
                    break;
                }

//            lbcode_id.setText(String.valueOf(depotDao.id_code()));
//            PaintData.removeAll(Collections.singleton(true));
            }
        }
        ReportsController.createPDF_xuatkho(member,PaintData,id_code.getText(),String.valueOf(tong));
    }
}
