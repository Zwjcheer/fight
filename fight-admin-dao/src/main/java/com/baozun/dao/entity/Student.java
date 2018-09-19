package com.baozun.dao.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Student {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机phone
     */
    @Column(name = "SNAME")
    private String sname;

    /**
     * type
     */
    @Column(name = "SAGE")
    private String sage;

    /**
    
     */
    @Column(name = "SEX")
    private String sex;

    /**
    
     */
    @Column(name = "TENANT_COUNTRY")
    private String tenantCountry;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getSname() {

        return sname;
    }

    public void setSname(String sname) {

        this.sname = sname;
    }

    public String getSage() {

        return sage;
    }

    public void setSage(String sage) {

        this.sage = sage;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {

        this.sex = sex;
    }

    public String getTenantCountry() {

        return tenantCountry;
    }

    public void setTenantCountry(String tenantCountry) {

        this.tenantCountry = tenantCountry;
    }

}