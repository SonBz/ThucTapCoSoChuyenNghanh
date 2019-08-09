package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.ItemHome;
import com.hvktmm.at12i.model.ItemPaint;
import com.hvktmm.at12i.model.Paint;
import com.hvktmm.at12i.utils.Pass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintDao {
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    Connection connection = null;
    Pass pass = new Pass();

    private Connection getConnection() throws SQLException {
        Connection con;
        con = MySqlDao.getInstance().getConnection();
        return con;
    }
    public Paint check(String name) {
        Paint paint = null;
        try {
            connection = getConnection();

            String sql = "select * from paint where name=?";
            ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1, name);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                paint = new Paint();
                paint.setId(resultSet.getInt("id"));
                paint.setName(resultSet.getString("name"));
                paint.setType(resultSet.getString("type"));
                paint.setSize(resultSet.getString("size"));
                paint.setProducer(resultSet.getString("producer"));
                paint.setColor(resultSet.getString("color"));
                paint.setPrice(resultSet.getFloat("price"));
                return paint;
            } else {
                return paint;
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
        return paint;
    }
    public boolean insertpaint(Paint paint) {
        boolean check = false;
        try {
            connection=getConnection();
            String sql="insert into paint(id,name,type,size,color,producer,price) values (?,?,?,?,?,?,?)";
            ptmt=connection.prepareStatement(sql);
            ptmt.setInt(1,paint.getId());
            ptmt.setString(2,paint.getName());
            ptmt.setString(3,paint.getType());
            ptmt.setString(4,paint.getSize());
            ptmt.setString(6,paint.getProducer());
            ptmt.setString(5,paint.getColor());
            ptmt.setFloat(7,paint.getPrice());
            int row=0;
            row=ptmt.executeUpdate();
            if (row!=0){
                check=true;
            }
            else{
                check=false;
            }
            return check;
        }
         catch(SQLException e) {
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

    public Integer id_code(){
        int code=0;
        try {
            String sql = "select * from paint where id=(SELECT max(id) FROM paint)";
            ptmt = getConnection().prepareStatement(sql);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                code=resultSet.getInt("id");
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

    public List<ItemPaint> table_list(){
        try {
            connection=getConnection();
            String sql="select * from paint";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            List<ItemPaint> list=new ArrayList<>();

            while (resultSet.next()){
                String id=String.valueOf(resultSet.getInt("id"));
                String name=resultSet.getString("name");
                String type=resultSet.getString("type");
                String size=resultSet.getString("size");
                String color=resultSet.getString("color");
                String producer= resultSet.getString("producer");
                String price=String.valueOf(resultSet.getFloat("price"));
                ItemPaint itemHome=new ItemPaint(id,name,type,size,color,producer,price);
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
}
