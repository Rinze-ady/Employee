package com.project.frame;

import com.lovo.netCRM.component.*;
import com.project.bean.EmployeeBean;
import com.project.service.impl.EmployeeServiceImpl;
import com.project.service.IEmployeeService;

import javax.swing.*;

/**
 * 修改窗体
 */
public class UpdateFrame extends JFrame {
    /**姓名标签*/
    private LovoLabel nameTxt = new LovoLabel("姓名",50,100,this);
    /**生日标签*/
    private LovoLabel birthdayTxt = new LovoLabel("生日",50,140,this);
    /**职位文本框*/
    private LovoTxt jobTxt = new LovoTxt("职位",50,180,this);
    /**工资文本框*/
    private LovoTxt moneyTxt = new LovoTxt("工资",50,220,this);
    /**入职时间标签*/
    private LovoLabel joinDateTxt = new LovoLabel("入职时间",50,260,this);
    /**头像图片*/
    private LovoImageLabel faceImg = new LovoImageLabel(300,100,150,150,this);
    /**要修改的员工编号*/
    private int id;

    /**员工业务接口实现类*/
    private IEmployeeService service = new EmployeeServiceImpl();

    public UpdateFrame(int id){
        this.id = id;
        this.setLayout(null);
        this.init();

        this.setSize(600,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }

    private void init() {
        initInfo();
        new LovoTitleLabel("修改员工",this);
        LovoButton updateButton = new LovoButton("修改",240,300,this);
        updateButton.addActionListener(e -> updateEmployee());
    }

    //--------


    /**
     * 初始化信息
     */
    private void initInfo() {
        //按ID得到员工对象
        EmployeeBean em = service.findByID(id);
        nameTxt.setText(em.getName());
        this.birthdayTxt.setText(em.getBirthday().toString());
        this.jobTxt.setText(em.getJob());
        this.moneyTxt.setText(em.getMoney() + "");
        this.joinDateTxt.setText(em.getJoinDate().toString());
        //设置图片的路径
        this.faceImg.setImage("image/"+em.getImgPath());
    }
    /**
     * 修改员工信息
     */
    private void updateEmployee() {
        service.update(id, jobTxt.getText(),
                Integer.parseInt(moneyTxt.getText()));

        this.dispose();
        new MainFrame();
    }
}
