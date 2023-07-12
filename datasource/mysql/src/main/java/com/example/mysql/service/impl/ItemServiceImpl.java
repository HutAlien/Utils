package com.example.mysql.service.impl;

import com.example.mysql.domain.entity.Item;
import com.example.mysql.mapper.ItemMapper;
import com.example.mysql.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author fengyunjun
 * @since 2023-07-11
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
