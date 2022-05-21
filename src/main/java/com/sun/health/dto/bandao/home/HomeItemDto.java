package com.sun.health.dto.bandao.home;

import com.sun.health.dto.BaseDTO;

public class HomeItemDto extends BaseDTO {

    private String name;
    private Long imageId;

    public HomeItemDto(String name) {
        this.name = name;
    }

    public HomeItemDto(String name, Long imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
