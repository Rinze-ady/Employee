package com.project.service.impl;

import com.project.bean.EmployeeBean;
import com.project.dao.IEmployeeDao;
import com.project.dao.impl.EmployeeDaoImpl;
import com.project.service.IEmployeeService;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工业务接口实现类
 */
public class EmployeeServiceImpl implements IEmployeeService {
    //员工持久层接口实现类对象
    //依赖倒置原则，接口引用接口实现类对象
    private IEmployeeDao dao = new EmployeeDaoImpl();

    @Override
    public List<EmployeeBean> findByItem(String name, LocalDate startDate, LocalDate endDate) {
        return dao.findByItem(name, startDate, endDate);
    }

    @Override
    public void add(EmployeeBean employee) {
        dao.add(employee);

    }

    @Override
    public void del(int id) {
        dao.del(id);

    }

    @Override
    public EmployeeBean findByID(int id) {
        return dao.findByID(id);
    }

    @Override
    public void update(int id, String job, int money) {
        dao.update(id, job, money);

    }
}
