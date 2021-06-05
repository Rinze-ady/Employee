package com.project.dao.impl;

import com.project.bean.EmployeeBean;
import com.project.dao.BaseDao;
import com.project.dao.IEmployeeDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工持久层接口的实现类
 */
public class EmployeeDaoImpl extends BaseDao implements IEmployeeDao {
    @Override
    public List<EmployeeBean> findByItem(String name, LocalDate startDate, LocalDate endDate) {

        List<EmployeeBean> list = new ArrayList<>();
        String str = "select * from t_employee where 1=1 ";

        List strList = new ArrayList();

        if(name != null && name.length() != 0){
            str += "and em_name like ? ";
            strList.add("%"+name+"%");
        }

        if(startDate != null ){
            str += "and em_birthday >= ? ";
            strList.add(startDate);
        }

        if(endDate != null ){
            str += "and em_birthday <= ?";
            strList.add(endDate);
        }

        this.setConnection();

        try {
            ps = con.prepareStatement(str);
            for(int i=0; i<strList.size(); i++){
                ps.setObject(i+1, strList.get(i));
            }

            rs = ps.executeQuery();
            while (rs.next()){
                list.add(fullEmployee());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

        return list;
    }

    @Override
    public void add(EmployeeBean employee) {

        this.setConnection();

        try {
            ps = con.prepareStatement("insert into t_employee(em_name, em_birthday, em_job, em_money, em_joinDate, em_imgPath)values(?,?,?,?,?,?)");
            ps.setObject(1,employee.getName());
            ps.setObject(2,employee.getBirthday());
            ps.setObject(3,employee.getJob());
            ps.setObject(4,employee.getMoney());
            ps.setObject(5,employee.getJoinDate());
            ps.setObject(6, employee.getImgPath());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }
    }

    @Override
    public void del(int id) {
        this.setConnection();

        try {
            ps = con.prepareStatement("delete from t_employee where pk_employeeId = ?");
            ps.setObject(1, id);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    @Override
    public EmployeeBean findByID(int id) {
        EmployeeBean em = new EmployeeBean();
        this.setConnection();

        try {
            ps = con.prepareStatement("select*from t_employee where pk_employeeId = ?");
            ps.setObject(1,id);

            rs = ps.executeQuery();
            if(rs.next()){
                em = fullEmployee();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

        return em;
    }

    @Override
    public void update(int id, String job, int money) {

        this.setConnection();

        try {
            ps = con.prepareStatement("update t_employee set em_job = ?, em_money = ? where pk_employeeId = ?");
            ps.setObject(1, job);
            ps.setObject(2, money);
            ps.setObject(3, id);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            this.closeConnection();
        }

    }

    /**
     *从结果集中得到数据，封装实体对象
     * @return
     */
    private EmployeeBean fullEmployee(){
        EmployeeBean em = new EmployeeBean();

        try {
            em.setId(rs.getInt("pk_employeeId"));
            em.setName(rs.getString("em_name"));
            em.setJob(rs.getString("em_job"));
            em.setMoney(rs.getInt("em_money"));
            em.setBirthday(LocalDate.parse(rs.getString("em_birthday")));
            em.setImgPath(rs.getString("em_imgPath"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return em;
    }

    public static void main(String[] args) {
        //依赖倒置原则，用接口引用实现类
        IEmployeeDao em = new EmployeeDaoImpl();
//        System.out.println(em.findByItem("",null, null));
//        em.add(new EmployeeBean("jony",5000 , "员工", LocalDate.parse("1995-05-04"), "3.gif" ));
//        em.del(9);
//        em.update(4,"助理", 6700);
        System.out.println(em.findByID(3))  ;
    }
}
