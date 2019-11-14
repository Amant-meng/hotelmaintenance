package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_student")
public class Tstudent {
    private int student_id;
    private String student_name;
    private String sudent_class;
    private Tscore tscore;

    public void setTscore(Tscore tscore) {
        this.tscore = tscore;
    }

    public Tscore getTscore() {
        return tscore;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setSudent_class(String sudent_class) {
        this.sudent_class = sudent_class;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getSudent_class() {
        return sudent_class;
    }
}
