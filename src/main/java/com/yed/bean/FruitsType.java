package com.yed.bean;

import java.io.Serializable;

public class FruitsType implements Serializable {

    private static final long serialVersionUID = 4353229397528896776L;


    private Integer  fid;
    private Integer tid;




    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "FruitsType{" +

                ", fid=" + fid +
                ", tid=" + tid +
                '}';
    }

    public FruitsType(Integer fid, Integer tid) {
        this.fid = fid;
        this.tid = tid;
    }
}
