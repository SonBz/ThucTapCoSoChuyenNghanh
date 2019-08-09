package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.utils.DateUtil;
import com.hvktmm.at12i.utils.Pass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OderDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }

    public Boolean insert(int id_member,int id_code_id,int id_user,float price,int id){
        boolean check=false;
        try {
            connection=getConnection();
            String sql="insert into oder(id,id_member,id_code_id,id_user,price,day_trading) values (?,?,?,?,?,?)";
            ptmt=getConnection().prepareStatement(sql);
            ptmt.setInt(1,id);
            ptmt.setInt(2,id_member);
            ptmt.setInt(3,id_code_id);
            ptmt.setInt(4,id_user);
            ptmt.setFloat(5,price);
            ptmt.setString(6,DateUtil.getCurrtentTime());
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

//    public
public Integer id_code(){
    int code=0;
    try {
        String sql = "SELECT max(id_code_id) as code FROM qlbs.oder where id_code_id like '9%'";
        ptmt = getConnection().prepareStatement(sql);
        resultSet = ptmt.executeQuery();
        if (resultSet.next()) {

            code=resultSet.getInt("code");

            return code;
        } else {
            return code;
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
    return code;
}
}
