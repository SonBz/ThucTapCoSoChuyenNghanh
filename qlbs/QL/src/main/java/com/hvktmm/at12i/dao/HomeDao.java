package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.Details;
import com.hvktmm.at12i.model.ItemHome;
import com.hvktmm.at12i.utils.Pass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }

    public List<ItemHome> table_list(){
        try {
            connection=getConnection();
            String sql="select a.id,a.name,b.type,b.size,b.color,a.amount,a.amount*b.price as total,a.status from paint b,depot a where b.id=a.id_paint";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            List<ItemHome> list=new ArrayList<>();

            while (resultSet.next()){
//                System.out.println("check");
                String id=String.valueOf(resultSet.getInt("id"));
                String name=resultSet.getString("name");
                String type=resultSet.getString("type");
                String size=resultSet.getString("size");
                String color=resultSet.getString("color");
                String amount= String.valueOf(resultSet.getInt("amount"));
                String total=String.valueOf(resultSet.getInt("total"))+" vnđ";
                String status=resultSet.getString("status");
                String a="";
//                System.out.println(amount);
                if (status.equals("1")){
                    a="còn hàng";
                }
                else if (status.equals("0")){
                    a="hết hàng";
                }
                ItemHome itemHome=new ItemHome(id,name,type,size,color,amount,total,a);
                list.add(itemHome);
            }
            return list;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Details details(String id){
        Details details=null;
        try {
            connection=getConnection();
            String sql="select a.name,c.name,b.date_in,b.date_out,c.producer,c.price from user a,depot b,paint c where c.id=b.id_paint and b.id_user=a.id and b.id=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,Integer.valueOf(id));
            resultSet=ptmt.executeQuery();
            if (resultSet.next()){
                details=new Details();
                details.setUser(resultSet.getString("name"));
                details.setNamepaint(resultSet.getString("name"));
                details.setDate_in(resultSet.getString("date_in"));
                details.setPrice(String.valueOf(resultSet.getFloat("price"))+" vnđ");
                details.setProduce(resultSet.getString("producer"));
                if (resultSet.getString("date_out")==null){
                    details.setDate_out("");
                    System.out.println("done");
                }
                else {
                    details.setDate_out(resultSet.getString("date_out"));
                }
                return details;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) {
        HomeDao a=new HomeDao();
        System.out.println(a.details("2").toString());
    }
}
