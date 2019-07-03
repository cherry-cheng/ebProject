package com.leyou.search.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface GoodsClient {
    // 将goodscontroller中的接口搞过来
}
