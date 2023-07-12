package com.example.mysql.mapper;

import com.example.mysql.domain.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author fengyunjun
 * @since 2023-07-12
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
