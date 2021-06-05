package com.project.frame;

import com.lovo.netCRM.component.*;
import com.project.bean.EmployeeBean;
import com.project.service.impl.EmployeeServiceImpl;
import com.project.service.IEmployeeService;
import com.project.util.TypeChange;

import javax.swing.*;
import java.util.List;

/**
 * 主窗体
 */
public class MainFrame extends JFrame {
    /**表格组件*/
    private LovoTable table;
    /**姓名文本框*/
    private LovoTxt nameTxt;
    /**起始日期*/
    private LovoTxt startTxt;
    /**结束日期*/
    private LovoTxt endTxt;

    /**员工业务组件*/
    //依赖倒置原则，接口引用实现类
    private IEmployeeService service = new EmployeeServiceImpl();

    public MainFrame(){
        this.setLayout(null);
        this.init();

        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MainFrame();
    }


    private void init(){
        LovoButton addButton = new LovoButton("添加",80,300,this);
        LovoButton delButton = new LovoButton("删除",80,350,this);
        LovoButton updateButton = new LovoButton("修改",80,400,this);
        addButton.addActionListener(e -> {this.dispose();new AddFrame();});
        delButton.addActionListener(e -> this.delEmployee());
        updateButton.addActionListener(e -> this.updateEmployee());

        LovoTitlePanel tp = new LovoTitlePanel("查询员工信息",
                300,300,400,240,this);
        this.nameTxt = new LovoTxt("姓名",50,30,tp);
        this.startTxt = new LovoTxt("生日起始日期",50,80,tp);
        this.endTxt = new LovoTxt("生日结束日期",50,130,tp);
        LovoButton findButton = new LovoButton("查询",150,180,tp);
        findButton.addActionListener(e -> findEmployee());
        initTable();
    }
    //--------------------
    /**
     * 初始化表格
     */
    private void initTable(){
        new LovoTitleLabel("员工管理",this);
        table = new LovoTable(this,
                new String[]{"姓名","生日","职位","工资"},
                //注意下面string[]种的参数
                new String[]{"name", "birthday","job","money"},"id");
        table.setSizeAndLocation(50,100,650,180);
        this.findEmployee();
    }

    /**
     * 修改员工
     */
    private void updateEmployee() {
        int id = table.getKeyByInt();
        if(id == -1){
            JOptionPane.showMessageDialog(null,"请选择行");
            return;
        }


        this.dispose();
        new UpdateFrame(id);
    }

    /**
     * 删除员工
     */
    private void delEmployee() {
        //得到点中行对应的id属性值

        int id = table.getKeyByInt();
        if(id == -1){
            JOptionPane.showMessageDialog(null,"请选择行");
            return;
        }


        int item = JOptionPane.showConfirmDialog(null, "是否确认删除");
        if(item == 0){
            service.del(id);
            this.findEmployee();
        }
    }

    /**
     * 查询员工
     */
    private void findEmployee() {

        List<EmployeeBean> list = service.findByItem(nameTxt.getText(),
                TypeChange.getDate(startTxt.getText()),
                TypeChange.getDate(endTxt.getText()));

        table.updateLovoTable(list);
    }
}
