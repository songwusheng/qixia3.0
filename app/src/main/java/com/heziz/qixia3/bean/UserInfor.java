package com.heziz.qixia3.bean;



import java.io.Serializable;

/**
 * 用户信息
 */
public class UserInfor implements Serializable{


    /**
     * uuid : 865ce0d4-e1da-43e7-bc9e-2ed52b48f2a9
     * account : admin
     * station : null
     * managerId : null
     * position : 3
     * workStatus
     * name
     */

    private String uuid;
    private String account;
    private Object station;
    private Object managerId;
    private String position;
    private String name;
    private String workStatus;
    private JPushCommBean map;

    public JPushCommBean getMap() {
        return map;
    }

    public void setMap(JPushCommBean map) {
        this.map = map;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Object getStation() {
        return station;
    }

    public void setStation(Object station) {
        this.station = station;
    }

    public Object getManagerId() {
        return managerId;
    }

    public void setManagerId(Object managerId) {
        this.managerId = managerId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UserInfor{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", station=" + station +
                ", managerId=" + managerId +
                ", position='" + position + '\'' +
                '}';
    }
}
