package com.mamba.shop.entity.custom_entity.dto;

import com.mamba.shop.entity.Authorities;
import com.mamba.shop.entity.User;

import java.io.Serializable;

public class UserDto implements Serializable{
    private String fullName;
    private String nickName;
    private String roleInSystem;
    private boolean enabled;
    private String cash;
    private String orderAmount;//всего заказов на сумму
    private String summaryOfDebt; //остаток долга у пользователя
    private String actingOrder;//активные ордера

    public UserDto(User user) {
        this.nickName = user.getUsername();
        this.enabled = user.isEnabled();
        this.cash = String.valueOf(user.getCash());
        this.fullName = "full: " + user.getUsername();
        this.roleInSystem = "user";
        this.orderAmount = "0";
        this.summaryOfDebt = "0";
        this.actingOrder = "0";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoleInSystem() {
        return roleInSystem;
    }

    public void setRoleInSystem(String roleInSystem) {
        this.roleInSystem = roleInSystem;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getSummaryOfDebt() {
        return summaryOfDebt;
    }

    public void setSummaryOfDebt(String symmaryOfDebt) {
        this.summaryOfDebt = symmaryOfDebt;
    }

    public String getActingOrder() {
        return actingOrder;
    }

    public void setActingOrder(String actingOrder) {
        this.actingOrder = actingOrder;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "fullName='" + fullName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roleInSystem='" + roleInSystem + '\'' +
                ", enabled=" + enabled +
                ", cash='" + cash + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", summaryOfDebt='" + summaryOfDebt + '\'' +
                ", actingOrder='" + actingOrder + '\'' +
                '}';
    }
}