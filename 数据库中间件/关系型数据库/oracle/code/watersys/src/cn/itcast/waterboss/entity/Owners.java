package cn.itcast.waterboss.entity;

import java.util.Date;

//业主表实体类
public class Owners {

    private Long id;//编号
    private String name;//业主名称
    private Long address;//地址ID
    private String houseNumber;//门牌号
    private String waterMeter;//水表编号
    private Date addDate;//增加日期
    private Long ownerTypeId;//业主类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(String waterMeter) {
        this.waterMeter = waterMeter;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Long getOwnerTypeId() {
        return ownerTypeId;
    }

    public void setOwnerTypeId(Long ownerTypeId) {
        this.ownerTypeId = ownerTypeId;
    }
}
