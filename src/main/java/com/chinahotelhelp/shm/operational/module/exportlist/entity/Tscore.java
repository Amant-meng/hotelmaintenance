package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_score")
public class Tscore {
    private int score_id;
    private int chinese;
    private int math;
    private int english;
    private int history;


    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public void setHistory(int history) {
        this.history = history;
    }



    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public int getScore_id() {
        return score_id;
    }


    public int getChinese() {
        return chinese;
    }

    public int getMath() {
        return math;
    }

    public int getEnglish() {
        return english;
    }

    public int getHistory() {
        return history;
    }
}
