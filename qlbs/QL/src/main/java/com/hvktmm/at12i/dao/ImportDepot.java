package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.Depot;
import com.hvktmm.at12i.utils.DateUtil;
import com.hvktmm.at12i.utils.Pass;
import com.hvktmm.at12i.utils.StatusUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportDepot {

    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }


    public Depot check(String id){
        Depot depot=null;
        try {
            depot=new Depot();
            connection=getConnection();
            String sql="select amount,status from depot where id=?";
            ptmt.setString(1,id);
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            if (resultSet.next()){
                depot.setAmount(resultSet.getInt("amount"));
                depot.setStatus(resultSet.getString("status"));
                return depot;
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


}
