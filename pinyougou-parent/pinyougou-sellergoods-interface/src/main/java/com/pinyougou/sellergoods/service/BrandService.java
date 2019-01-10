package com.pinyougou.sellergoods.service;


import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;
import java.util.Map;

/**
 * 品牌服务层接口
 */
public interface BrandService {

    /**
     * 返回全部列表
     *
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 返回分页列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(Integer pageNum, Integer pageSize);

    /**
     * 增加品牌
     *
     * @param brand
     */
    public void add(TbBrand brand);

    /**
     * 修改品牌
     *
     * @param brand
     */
    public void update(TbBrand brand);

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 批量删除
     *
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 分页(条件查询)
     *
     * @param brand
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize);

    /**
     * 品牌下拉框数据
     *
     * @return
     */
    List<Map> selectOptionList();
}
