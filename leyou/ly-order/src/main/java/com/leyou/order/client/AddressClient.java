package com.leyou.order.client;

import com.leyou.order.dto.AddressDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class AddressClient {
    // 地址未做处理，故用假数据加工，真实情况下需要通过feign调用地址微服务接口
    public static final List<AddressDTO> addressList = new ArrayList<AddressDTO>() {
        {
            AddressDTO address = new AddressDTO();
            address.setId(1L);
            address.setAddress("航头镇航头路18号传智播客 3号楼");
            address.setCity("上海");
            address.setDistrict("浦东新区");
            address.setName("胡歌");
            address.setPhone("18501605964");
            address.setState("上海");
            address.setZipCode("210000");
            address.setIsDefault(true);
            add(address);

            AddressDTO address2 = new AddressDTO();
            address2.setId(2L);
            address2.setAddress("天堂路 3号楼");
            address2.setCity("上海");
            address2.setDistrict("浦东新区");
            address2.setName("张三");
            address2.setPhone("18501605964");
            address2.setState("上海");
            address2.setZipCode("210000");
            address2.setIsDefault(true);
            add(address2);
        }
    };

    public static AddressDTO findById(Long id) {
        for (AddressDTO addressDTO : addressList) {
            if (addressDTO.getId() == id) {
                return addressDTO;
            }
        }
        return null;
    }
}
