package com.hvktmm.at12i.dao;

import com.hvktmm.at12i.config.MySqlDao;
import com.hvktmm.at12i.model.ItemMember;
import com.hvktmm.at12i.model.Member;
import com.hvktmm.at12i.utils.Pass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    PreparedStatement ptmt=null;
    ResultSet resultSet=null;
    Connection connection=null;
    Pass pass=new Pass();
    private Connection getConnection() throws SQLException {
        Connection con;
        con=MySqlDao.getInstance().getConnection();
        return con;
    }

    public Member check_member(String phone,String status){
        Member member=null;
        try {
            connection=getConnection();
            String sql="select * from member where status_member=? and phone=?";
            ptmt=getConnection().prepareStatement(sql);
            ptmt.setString(1,status);
            ptmt.setString(2,phone);
            resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                member=new Member();
                member.setId(resultSet.getInt("id"));
                member.setName(resultSet.getString("name"));
                member.setPhone(resultSet.getString("phone"));
                member.setAddress(resultSet.getString("address"));
                return member;
            } else {
                return member;
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
        return member;
    }

    public Boolean insert(Member member){
        boolean check=false;
        MemberDao memberDao=new MemberDao();
        try {
            connection=getConnection();
            String sql="insert into member(id,name,address,phone,status_member) values (?,?,?,?,?)";
            ptmt=getConnection().prepareStatement(sql);
//            ptmt.setInt(1,memberDao.id_code()+1);
            ptmt.setInt(1,member.getId());
            ptmt.setString(2,member.getName());
            ptmt.setString(4,member.getPhone());
            ptmt.setString(3,member.getAddress());
            ptmt.setString(5,member.getStatus_member());
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

    public Integer id_code(){
        int code=0;
        try {
            String sql = "select * from member where id=(SELECT max(id) FROM member)";
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

    public List<ItemMember> list_member(String status){
        ItemMember member=null;
        List<ItemMember> list=new ArrayList<>();
        try {
            connection=getConnection();
            String sql="select * from member where status_member=?";
            ptmt=getConnection().prepareStatement(sql);
            ptmt.setString(1,status);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {

                String id=String.valueOf(resultSet.getInt("id"));
                String name=resultSet.getString("name");
                String phone=resultSet.getString("phone");
                String add=resultSet.getString("address");
                member=new ItemMember(id,name,add,phone);
                list.add(member);
            }
            return list;
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
        MemberDao a=new MemberDao();
        Member b=a.check_member("00002","0");
        System.out.println(b.toString());
    }
}
