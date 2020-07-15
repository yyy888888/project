package com.yed.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Fruits implements Serializable {
    private static final long serialVersionUID = -7098854987438619132L;

    private Integer id;
    private String name;
    private String pic;
    private BigDecimal price;
    private BigDecimal sprice;
    private BigDecimal eprice;
    private String address;
    private String chuan;
    private Integer sheng;
    private Integer shi;
    private Integer[] cxids;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date cdate;

    public Integer getSheng() {
        return sheng;
    }

    public void setSheng(Integer sheng) {
        this.sheng = sheng;
    }

    public Integer getShi() {
        return shi;
    }

    public void setShi(Integer shi) {
        this.shi = shi;
    }

    public Integer[] getCxids() {
        return cxids;
    }

    public void setCxids(Integer[] cxids) {
        this.cxids = cxids;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChuan() {
        return chuan;
    }

    public void setChuan(String chuan) {
        this.chuan = chuan;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public BigDecimal getSprice() {
        return sprice;
    }

    public void setSprice(BigDecimal sprice) {
        this.sprice = sprice;
    }

    public BigDecimal getEprice() {
        return eprice;
    }

    public void setEprice(BigDecimal eprice) {
        this.eprice = eprice;
    }

    @Override
    public String toString() {
        return "Fruits{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", price=" + price +
                ", sprice=" + sprice +
                ", eprice=" + eprice +
                ", address='" + address + '\'' +
                ", chuan='" + chuan + '\'' +
                ", sheng=" + sheng +
                ", shi=" + shi +
                ", cxids=" + Arrays.toString(cxids) +
                ", cdate=" + cdate +
                '}';
    }

    public String getFileName(){
        if (this.getPic()!=null){
            return pic.replaceAll("http://127.0.0.1:8081/","");
        }
        return null;
    }
}
