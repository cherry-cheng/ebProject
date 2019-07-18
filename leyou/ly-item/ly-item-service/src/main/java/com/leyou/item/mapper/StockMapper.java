package com.leyou.item.mapper;

import com.leyou.item.pojo.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface StockMapper extends com.leyou.common.mapper.BaseMapper<Stock> {
    @Update("update tb_stock set stock = stock = #{num} where sku_id = #{id} and stock >= #{num}")
    int decreaseStock(@Param("id") Long id, @Param("num") Integer num);
}
