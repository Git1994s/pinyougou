package com.pinyougou.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 品牌Controller
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {

        return brandService.findAll();
    }

    /**
     * 返回全部列表(分页)
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(Integer page, Integer rows) {
        return brandService.findPage(page, rows);
    }

    /**
     * 品牌增加
     *
     * @param brand
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }

    }

    /**
     * 修改品牌
     *
     * @param brand
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }

    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findOne(id);

    }

    /**
     * 删除品牌列表
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 分页查询
     *
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand, Integer page, Integer rows) {
        return brandService.findPage(brand, page, rows);
    }

    /**
     * 品牌下拉框数据
     *
     * @return
     */
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();

    }
}
