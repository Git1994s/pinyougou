package com.pinyougou.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 服务层实现
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    /**
     * 返回全部列表
     *
     * @return
     */
    @Override
    public List<TbBrand> findAll() {

        return brandMapper.selectByExample(null);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);//分页

        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加品牌
     *
     * @param brand
     */
    @Override
    public void add(TbBrand brand) {

        brandMapper.insert(brand);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbBrand findOne(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除品牌
     *
     * @param ids
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 修改品牌列表
     *
     * @param brand
     */
    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    /**
     * 条件分页查询
     *
     * @param brand
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//分页
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (brand.getName() != null && brand.getName().length() > 0) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
                criteria.andFirstCharLike("%" + brand.getFirstChar() + "%");
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 品牌下拉框数据
     *
     * @return
     */
    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }
}
