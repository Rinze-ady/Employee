package com.project.frame;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoFileChooser;
import com.lovo.netCRM.component.LovoTitleLabel;
import com.lovo.netCRM.component.LovoTxt;
import com.project.bean.EmployeeBean;
import com.project.service.impl.EmployeeServiceImpl;
import com.project.service.IEmployeeService;
import com.project.util.FileUtil;
import com.project.util.TypeChange;

import javax.swing.*;

/**
 * 添加窗体
 */
public class AddFrame extends JFrame {
    /**姓名文本框*/
    private LovoTxt nameTxt = new LovoTxt("姓名",50,100,this);
    /**生日文本框*/
    private LovoTxt birthdayTxt = new LovoTxt("生日",50,140,this);
    /**职位文本框*/
    private LovoTxt jobTxt = new LovoTxt("职位",50,180,this);
    /**工资文本框*/
    private LovoTxt moneyTxt = new LovoTxt("工资",50,220,this);
    /**文件选择器*/
    private LovoFileChooser faceChooser = new LovoFileChooser(this);

    //每个表现层调业务组件对象，业务层掉持久层对象
    /**员工业务接口实现类*/
    private IEmployeeService service = new EmployeeServiceImpl();

    public AddFrame(){
        this.setLayout(null);
        this.init();

        this.setSize(600,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }

    private void init() {
        new LovoTitleLabel("添加员工",this);
        faceChooser.setBounds(300,100,150,150);
        LovoButton addButton = new LovoButton("添加",240,280,this);
        addButton.addActionListener(e -> addEmployee());
    }

    //--------

    /**
     * 添加员工
     */
    private void addEmployee() {
        //得到选择文件的路径
        String filePath = this.faceChooser.getFilePath();
        //将选择文件拷贝到目标目录种，得到重命名的文件
        String fileName = FileUtil.copyFile(filePath, "image/");


        EmployeeBean em = new EmployeeBean();
        em.setName(nameTxt.getText());
        em.setBirthday(TypeChange.getDate(birthdayTxt.getText()));
        em.setMoney(Integer.parseInt(moneyTxt.getText()));
        em.setJob(jobTxt.getText());
        em.setImgPath(fileName);

        service.add(em);

        this.dispose();
        new MainFrame();
    }
}
