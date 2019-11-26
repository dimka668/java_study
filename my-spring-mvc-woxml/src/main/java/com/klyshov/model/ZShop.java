package com.klyshov.model;

/**
 * Created by 16688641 on 21.11.2019.
 */
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zshop", namespace = "http://ibm.com/ru/spring-integration/demo/workspace")
public class ZShop implements Serializable {

    private static final long serialVersionUID = -6371123466652190899L;

    private String orderName;

    private int orderCount;

    public ZShop() {
    }

    public ZShop(String orderName, int orderCount) {
        this.orderName = orderName;
        this.orderCount = orderCount;
    }

    @XmlAttribute
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @XmlAttribute
    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
