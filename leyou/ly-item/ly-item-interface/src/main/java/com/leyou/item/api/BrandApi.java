package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BrandApi {
    /**
     * 根据cid查询品牌
     * @param cid
     * @return
     */
    @GetMapping("/cid/{cid}")
    List<Brand> queryBrandByCid(@PathVariable("cid") Long cid);

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable("id") Long id);
}
