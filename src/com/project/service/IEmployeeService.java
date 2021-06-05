package com.project.service;

import com.project.bean.EmployeeBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工业务层接口
 */
public interface IEmployeeService {
    /**
     * 动态条件查询员工
     * @param name 姓名
     * @param startDate 生日起始日期
     * @param endDate 生日结束日期
     * @return 员工集合
     */
    public List<EmployeeBean> findByItem(String name,
                                         LocalDate startDate, LocalDate endDate);

    /**
     * 添加员工
     * @param employee 员工对象
     */
    public void add(EmployeeBean employee);

    /**
     * 删除员工
     * @param id 员工ID
     */
    public void del(int id);


    //修改分两步操作，先按id号查询用户，查看源数据，即先做查询，在做修改
    //先做查询，返回一个员工对象

    /**
     * 按ID查询员工
     * @param id 编号
     * @return 员工对象
     */
    public EmployeeBean findByID(int id);

    /**
     * 按id修改员工的职位和工资
     * @param id 编号
     * @param job 新职位
     * @param money 新工资
     */
    public void update(int id, String job, int money);


}
