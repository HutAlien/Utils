package com.example.mysql.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.base.BaseResult;
import com.example.mysql.domain.entity.Item;
import com.example.mysql.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author fengyunjun
 * @since 2023-07-12
 */
@Api("商品信息表 服务")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    /**
     * 构造注入
     */
    private final IItemService itemService;

    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping
    public BaseResult<Boolean> add(@RequestBody Item item) {
        return BaseResult.status(itemService.save(item));
    }

    /**
     * 编辑
     */
    @ApiOperation("编辑")
    @PutMapping
    public BaseResult<Boolean> updateById(@RequestBody Item item) {
        return BaseResult.status(itemService.updateById(item));
    }

    /**
	 * 删除
	 */
    @DeleteMapping
    @ApiOperation(value = "逻辑删除", notes = "传入id")
    public BaseResult<Boolean> remove(@ApiParam(value = "id", required = true) @RequestParam String id){
        Item item = itemService.getById(id);
        if(item == null){
            return BaseResult.fail("无法删除，未找到对应的数据");
        }
        return BaseResult.status(itemService.removeById(id));
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/removeBatch")
    @ApiOperation(value = "批量逻辑删除", notes = "传入ids")
    public BaseResult<Boolean> removeBatch(@ApiParam(value = "ids", required = true) @RequestParam String ids){
        if(ids == null || "".equals(ids.trim())) {
            return BaseResult.fail("传递的参数无法识别");
        }
        return BaseResult.status(itemService.removeByIds(Arrays.asList(ids.split(","))));
    }

    /**
     * 通过id查询
     */
    @ApiOperation("通过id查询")
    @GetMapping
    public BaseResult<Item> queryById(@ApiParam(value = "id", required = true) @RequestParam String id) {
        return BaseResult.success(itemService.getById(id));
    }

    /**
     * 分页查询
     */
    @ApiOperation("分页查询")
    @GetMapping(value = "/queryPageList")
    public BaseResult<IPage<Item>> queryPageList(Item item,
                                                       @RequestParam(name="pageNum", defaultValue="1") Long pageNum,
                                                       @RequestParam(name="pageSize", defaultValue="10") Long pageSize){
        Wrapper<Item> queryWrapper = new QueryWrapper<>(item);
        Page<Item> page = new Page<>(pageNum, pageSize);
        IPage<Item> pageList = itemService.page(page, queryWrapper);
        return BaseResult.success(pageList);
    }

}
