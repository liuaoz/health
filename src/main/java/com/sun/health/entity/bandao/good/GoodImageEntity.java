package com.sun.health.entity.bandao.good;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_good_image")
public class GoodImageEntity {

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
}
