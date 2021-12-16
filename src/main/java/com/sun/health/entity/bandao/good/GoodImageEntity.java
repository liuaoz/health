package com.sun.health.entity.bandao.good;

import com.sun.health.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_good_image")
public class GoodImageEntity extends BaseEntity {

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 图片地址
     */
    private String link;

    /**
     * 图片描述
     */
    private String intro;

    /**
     * 图片类型： logo,icon,detail
     */
    private String type;

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
