package com.sun.health.dto.bandao.cart;

import com.sun.health.dto.bandao.good.GoodDto;

public class CartGoodDto {

    private Long id;

    private Long userId;

    private Integer quantity;

    private Boolean selected;

    private GoodDto good;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public GoodDto getGood() {
        return good;
    }

    public void setGood(GoodDto good) {
        this.good = good;
    }
}
