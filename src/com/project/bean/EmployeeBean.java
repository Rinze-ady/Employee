package com.project.bean;

import java.time.LocalDate;

public class EmployeeBean {
    private int id;
    private String name;
    private int money;
    private String job;
    private LocalDate birthday;
    private LocalDate joinDate = LocalDate.now();
    private String imgPath;

    public EmployeeBean() {
    }

    //id和入职时间可以不做初始化
    public EmployeeBean(String name, int money, String job,
                        LocalDate birthday, String imgPath) {
        this.name = name;
        this.money = money;
        this.job = job;
        this.birthday = birthday;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", job='" + job + '\'' +
                ", birthday=" + birthday +
                ", joinDate='" + joinDate + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}' + "\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
