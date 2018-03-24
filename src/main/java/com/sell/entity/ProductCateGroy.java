package com.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目表
 * Author: LDDFY
 * Date: 2018/3/24
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "product_category")
public class ProductCateGroy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categroyId;
    private String categroyName;
    private Integer categroyType;
    private Date createTime;
    private Date updateTime;

//TODO 使用@DATA注解可以不写get set方法 如果只使用get/set方法可使用@getter/@setter
//    /**
//     * 类目主键
//     */
//    public Integer getCategroyId() {
//        return categroyId;
//    }
//
//    public void setCategroyId(Integer categroyId) {
//        this.categroyId = categroyId;
//    }
//
//    /**
//     * 类目名称
//     */
//    public String getCategroyName() {
//        return categroyName;
//    }
//
//    public void setCategroyName(String categroyName) {
//        this.categroyName = categroyName;
//    }
//
//    /**
//     * 类目编号
//     */
//    public Integer getCategroyType() {
//        return categroyType;
//    }
//
//    public void setCategroyType(Integer categroyType) {
//        this.categroyType = categroyType;
//    }
//
//    /**
//     * 创建时间
//     */
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    /**
//     * 修改时间
//     */
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }

    @Override
    public String toString() {
        return "ProductCateGroy{" +
                "categroyId=" + categroyId +
                ", categroyName='" + categroyName + '\'' +
                ", categroyType=" + categroyType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
