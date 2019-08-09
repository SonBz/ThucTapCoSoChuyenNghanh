package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.History_buy;
import com.hvktmm.at12i.utils.DateUtil;
import com.hvktmm.at12i.utils.Pass;
import com.hvktmm.at12i.utils.StatusUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class History_buyDao {

    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }

    public Boolean insert_history_buy(int id_member,int id_oder,int depot,int count,float price,int code_id){
        boolean check=false;
        try {
            connection=getConnection();
            String sql="insert into history_buy(id_member,id_oder,id_depot,number_of_paint,price,code_id_depot) values (?,?,?,?,?,?)";
            ptmt=getConnection().prepareStatement(sql);
            ptmt.setInt(1,id_member);
            ptmt.setInt(2,id_oder);
            ptmt.setInt(3,depot);
            ptmt.setInt(4,count);
            ptmt.setFloat(5,price);
            ptmt.setInt(6,code_id);
            int row=0;
            row=ptmt.executeUpdate();
            if (row!=0){
                check=true;
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
        return check;



    }
}
