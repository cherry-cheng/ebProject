package com.leyou.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spuId;
    private String description;
    private String specialSpec;
    private String genericSpec;
    private String packingList;
    private String afterService;
}
