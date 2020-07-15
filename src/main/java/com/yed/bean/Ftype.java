package com.yed.bean;

import java.io.Serializable;

public class Ftype implements Serializable {
    private static final long serialVersionUID = -5430086568646848621L;

    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "Ftype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
