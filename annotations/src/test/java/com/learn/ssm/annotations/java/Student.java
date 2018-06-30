package com.learn.ssm.annotations.java;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
@JsonIgnoreProperties({"sex", "addTime"})
public class Student implements Serializable {

    @JsonProperty("str_id")
    private Integer id;

    @JsonIgnore
    private String name;

    private Integer age;

    private String sex;

    private Date addTime;


    @Override
    public String toString() {
        return "id" + id + "name" + name + "age" + age + "sex" + sex + "addTime" + addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

}
