package com.heziz.qixia3.bean;

import java.io.Serializable;

/**
 * Created by sws on 2019-04-22.
 * from:
 * describe:
 */

public class HomeBean implements Serializable {

    /**
     * total : 0
     * cost : 0
     * zhgd : 0
     * diff : 0
     * szg : 0
     * qzg : 0
     * fj : 0
     * sz : 0
     * yl : 0
     * jt : 0
     * sl : 0
     * dt : 0
     * qt : 0
     */

    private int total;
    private double cost;
    private int zhgd;
    private int diff;
    private int szg;
    private int qzg;
    private int fj;
    private int sz;
    private int yl;
    private int jt;
    private int sl;
    private int dt;
    private int qt;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getZhgd() {
        return zhgd;
    }

    public void setZhgd(int zhgd) {
        this.zhgd = zhgd;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public int getSzg() {
        return szg;
    }

    public void setSzg(int szg) {
        this.szg = szg;
    }

    public int getQzg() {
        return qzg;
    }

    public void setQzg(int qzg) {
        this.qzg = qzg;
    }

    public int getFj() {
        return fj;
    }

    public void setFj(int fj) {
        this.fj = fj;
    }

    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public int getYl() {
        return yl;
    }

    public void setYl(int yl) {
        this.yl = yl;
    }

    public int getJt() {
        return jt;
    }

    public void setJt(int jt) {
        this.jt = jt;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "total=" + total +
                ", cost=" + cost +
                ", zhgd=" + zhgd +
                ", diff=" + diff +
                ", szg=" + szg +
                ", qzg=" + qzg +
                ", fj=" + fj +
                ", sz=" + sz +
                ", yl=" + yl +
                ", jt=" + jt +
                ", sl=" + sl +
                ", dt=" + dt +
                ", qt=" + qt +
                '}';
    }
}
