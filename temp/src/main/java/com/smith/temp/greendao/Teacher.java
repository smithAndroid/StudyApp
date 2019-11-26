package com.smith.temp.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Teacher {
    @Id(autoincrement = true)
    long id;
    @Unique
    int teacherNum;
    int age;
    String name;
    @Generated(hash = 1450178147)
    public Teacher(long id, int teacherNum, int age, String name) {
        this.id = id;
        this.teacherNum = teacherNum;
        this.age = age;
        this.name = name;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getTeacherNum() {
        return this.teacherNum;
    }
    public void setTeacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
