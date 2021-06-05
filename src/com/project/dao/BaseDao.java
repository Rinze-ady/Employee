package com.project.dao;

import com.project.bean.EmployeeBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有DAO的父类
 */
public class BaseDao {
    protected Connection con;

    protected PreparedStatement ps;

    protected ResultSet rs;


    //加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立连接
     */
    public void setConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:6789/mydb；?characterEncoding=utf-8",
                    "root","lovo");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeConnection(){

        try {
            //关闭连接
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BaseDao dao = new BaseDao();
        dao.setConnection();
        System.out.println(dao.con);
    }
}
