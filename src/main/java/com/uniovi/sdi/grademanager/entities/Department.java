package com.uniovi.sdi.grademanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;

    private String faculty;
    private String phone;
    private int professors;

    public Department() {}

    public Department(Long id,String code, String name, String faculty, String phone, int professors) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.phone = phone;
        this.professors = professors;
    }

    public String getPhone() {
        return phone;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public int getProfessors() {
        return professors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProfessors(int professors) {
        this.professors = professors;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", faculty='" + faculty + '\'' +
                ", phone='" + phone + '\'' +
                ", professors=" + professors +
                '}';
    }

}
