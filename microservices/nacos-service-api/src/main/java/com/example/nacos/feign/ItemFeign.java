package com.example.nacos.feign;

import com.example.common.base.BaseResult;
import com.example.common.constant.ConstantFeignClientName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @Author: FengYunJun
 * @Date: 2023/7/27 16:25
 * @version:
 */
@FeignClient(name = ConstantFeignClientName.UTILS_NACOS_SERVICE)
public interface ItemFeign {

    /**
     * 查询商品信息
     *
     * @param id
     * @return BaseResult<List<String>>
     */
    @GetMapping("/item")
    BaseResult<List<String>> getItemList(@RequestParam String id);
}
