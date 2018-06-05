package com.snill.fm.bean;

import java.io.Serializable;

public class Permission implements Serializable, org.apache.shiro.authz.Permission {
    private int id;
    private String name;
    private String code;
    private int ptype;
    private String url;
    private String mapping;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    @Override
    public boolean implies(org.apache.shiro.authz.Permission p) {
        if(this.code == null || "".equals(this.code)){
            return false;
        }

        if(p instanceof Permission){
            Permission auth = (Permission)p;
            return this.code.equals(auth.getCode());
        }

        return false;
    }
}
