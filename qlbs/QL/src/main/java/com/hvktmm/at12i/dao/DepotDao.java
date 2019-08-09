package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.Depot;
import com.hvktmm.at12i.model.Paint;
import com.hvktmm.at12i.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepotDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }

    public Depot id_code(){
        Depot depot=null;
        try {
            String sql = "select * from depot where id=(SELECT max(id) FROM depot)";
            ptmt = getConnection().prepareStatement(sql);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                depot=new Depot();
                depot.setCode_id(resultSet.getInt("code_id"));
                depot.setId(resultSet.getInt("id"));
                return depot;
            } else {
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
        return depot;
    }


    public boolean insertdepot(Depot depot,int id_user,int code_id){
        boolean check=false;
        try {
            connection=getConnection();
            String sql="insert into depot(id,id_paint,id_user,name,date_in,date_out,amount,status,code_id) values (?,?,?,?,?,?,?,?,?)";
            ptmt=getConnection().prepareStatement(sql);
            ptmt.setInt(1,depot.getId());
            ptmt.setInt(2,depot.getId_paint());
            ptmt.setInt(3,id_user);
            ptmt.setString(4,depot.getName());
            ptmt.setString(5,DateUtil.fd());
            ptmt.setString(6,"null");
            ptmt.setInt(7,depot.getAmount());
            ptmt.setString(8,StatusUtil.conhang);
            ptmt.setInt(9,code_id);
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


    public List<Depot> check_id_paint(String id_paint){
        try {
            connection=getConnection();
            String sql="select * from depot where id_paint=? and status=?";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,Integer.valueOf(id_paint));
            ptmt.setString(2,StatusUtil.conhang);
//            ptmt.setInt(3,amount);
            resultSet=ptmt.executeQuery();
            java.util.List<Depot> list=new ArrayList<>();

            while (resultSet.next()){
                Depot depot=new Depot();
                depot.setId(resultSet.getInt("id"));
                depot.setId_paint(resultSet.getInt("id_paint"));
                depot.setCode_id(resultSet.getInt("code_id"));
                depot.setAmount(resultSet.getInt("amount"));
                list.add(depot);
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


    public Integer update(int id,int amount,String status){
        int check=3;
        try {
            connection=getConnection();
                    String sql="update depot set amount=? , status=? where id=?";
                    ptmt=connection.prepareStatement(sql);
                    ptmt.setInt(1,amount);
                    ptmt.setString(2,status);
                    ptmt.setInt(3,id);

                    int row=0;
                    row=ptmt.executeUpdate();
                    if (row!=0){
                        return 0;
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
        return check;//3 loi
    }

    public static void main(String[] args) {
        DepotDao b=new DepotDao();
        b.update(1,0,StatusUtil.hethang);
        System.out.println("done");
    }
}
