package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.Item_Statistical;
import com.hvktmm.at12i.utils.DateUtil;
import com.hvktmm.at12i.utils.Pass;
import com.hvktmm.at12i.utils.StatusUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticalDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }


    public List<Item_Statistical> table_list(){
        try {
            connection=getConnection();
            String sql="SELECT code_id,user.name,depot.date_in,depot.status,sum(amount) as number_of_paint,oder.price FROM qlbs.depot,qlbs.user,qlbs.oder where depot.id_user=user.id and depot.code_id=oder.id_code_id group by code_id ";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            List<Item_Statistical> list=new ArrayList<>();

            while (resultSet.next()){
                String code_id=String.valueOf(resultSet.getInt("code_id"));
                String name=resultSet.getString("name");
                String date=resultSet.getString("date_in");
                String s=resultSet.getString("status");
                String status="";
                if (s.equals("1")){
                    status="Còn Hàng";
                }
                else if (s.equals("0")){
                    status="Hết Hàng";
                }
                String count=String.valueOf(resultSet.getInt("number_of_paint"));
                String price=String.valueOf(resultSet.getFloat("price"));
                Item_Statistical itemHome=new Item_Statistical(code_id,name,date,status,count,price);
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


    public List<Item_Statistical> tk_xuatkho(){
        try {
            connection=getConnection();
            String sql="SELECT user.name,oder.day_trading,member.name as member,sum(number_of_paint) as count,oder.price FROM qlbs.member,qlbs.history_buy,qlbs.user,qlbs.oder where oder.id_user=user.id and history_buy.id_oder=oder.id and member.id=oder.id_member group by oder.id";
            ptmt=connection.prepareStatement(sql);
            resultSet=ptmt.executeQuery();
            List<Item_Statistical> list=new ArrayList<>();

            while (resultSet.next()){
                String user=resultSet.getString("name");
                String member=resultSet.getString("member");
                String date=resultSet.getString("day_trading");
                String price=resultSet.getString("price");
                String count=resultSet.getString("count");
                String status="";

                Item_Statistical itemHome=new Item_Statistical(user,member,date,count,price);
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

//    public List<Item_Statistical> tk_xuat_theonv(String id_user){
//        try {
//            connection=getConnection();
//            String sql="SELECT user.name,oder.day_trading,member.name as member,sum(number_of_paint) as count,oder.price FROM qlbs.member,qlbs.history_buy,qlbs.user,qlbs.oder where oder.id_user=user.id and history_buy.id_oder=oder.id and member.id=oder.id_member group by oder.id";
//            ptmt=connection.prepareStatement(sql);
//            resultSet=ptmt.executeQuery();
//            List<Item_Statistical> list=new ArrayList<>();
//
//            while (resultSet.next()){
//                String user=resultSet.getString("name");
//                String member=resultSet.getString("member");
//                String date=resultSet.getString("day_trading");
//                String price=resultSet.getString("price");
//                String count=resultSet.getString("count");
//                String status="";
//
//                Item_Statistical itemHome=new Item_Statistical(user,member,date,count,price);
//                list.add(itemHome);
//            }
//            return list;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public List<Item_Statistical> tk_nv(String status_order){
        try {
            connection=getConnection();
            String sql="SELECT d.id,sum(a.number_of_paint) as paint,sum(a.price) as total,d.name,c.day_trading FROM qlbs.history_buy a,qlbs.member b,qlbs.oder c,qlbs.user d  where d.id=c.id_user and c.id=a.id_oder and a.id_member=b.id and b.status_member=? group by d.id order by paint desc;";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,status_order);
            resultSet=ptmt.executeQuery();
            List<Item_Statistical> list=new ArrayList<>();

            while (resultSet.next()){
                String user=resultSet.getString("name");
                String price=resultSet.getString("total");
                String count=resultSet.getString("paint");
                String date=resultSet.getString("day_trading");
                Item_Statistical itemHome=new Item_Statistical(user,count,price,date);
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

    public List<Item_Statistical> tk_kh(String status_order){
        try {
            connection=getConnection();
            String sql="SELECT b.id,sum(a.number_of_paint) as paint,sum(a.price) as total,b.name,c.day_trading FROM qlbs.history_buy a,qlbs.member b,qlbs.oder c,qlbs.user d  where d.id=c.id_user and c.id=a.id_oder and a.id_member=b.id and b.status_member=? group by b.id order by paint desc;";
            ptmt=connection.prepareStatement(sql);
            ptmt.setString(1,status_order);
            resultSet=ptmt.executeQuery();
            List<Item_Statistical> list=new ArrayList<>();

            while (resultSet.next()){
                String user=resultSet.getString("name");
                String price=resultSet.getString("total");
                String count=resultSet.getString("paint");
                String date=resultSet.getString("day_trading");
                Item_Statistical itemHome=new Item_Statistical(user,count,price,date);
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

//    public static void main(String[] args) {
//        StatisticalDao a=new StatisticalDao();
//        List<Item_Statistical> b=a.tk_nv("0");
//        System.out.println(b.size());
//    }
}
