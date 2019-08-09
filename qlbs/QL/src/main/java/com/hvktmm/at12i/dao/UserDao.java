package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hvktmm.at12i.utils.Pass;
import com.hvktmm.at12i.utils.DateUtil;

public class UserDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }
    public User login(String username,String password){
        User user=null;

        try {

                connection=getConnection();
                String sql="select * from user where username=? and password=? and status=? ";
                ptmt=connection.prepareStatement(sql);
                ptmt.setString(1,username);
                ptmt.setString(2,pass.encrypt(password));
                ptmt.setString(3,"1");
                resultSet=ptmt.executeQuery();
                if (resultSet.next()){
                    user=new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setBirthday(resultSet.getString("birthday"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setRoot_status(resultSet.getString("root_status"));


                }
                return user;
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

    public static void main(String[] args) {
        UserDao a=new UserDao();

        User ab=a.login("cuongkma","cuong");
        System.out.println(ab.getName()+ab.getRoot_status()+ab.getId());
    }
}
