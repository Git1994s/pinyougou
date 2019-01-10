package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.transaction.annotation.Transactional;
import sun.tools.jstat.OptionOutputFormatter;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Specification specification) {
        //插入规格
        specificationMapper.insert(specification.getSpecification());
        for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
            //设置规格ID
            option.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(option);
        }

    }


    /**
     * 修改
     */
    @Override
    public void update(Specification specification) {

        //保存的规格
        specificationMapper.updateByPrimaryKey(specification.getSpecification());//保存规格
        //删除原来的规格
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        //指定规格ID为条件
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());
        //删除
        specificationOptionMapper.deleteByExample(example);
        //循环插入规格选项
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            specificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id) {
        //查询规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        //查询规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);//根据规格ID查询
        List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        Specification specification = new Specification();
        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptionList(optionList);
        return specification;
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
            //删除原有的规格选项
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            //指定规格ID为条件
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }


    @Override
    public PageResult findPage(TbSpecification specification, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        Criteria criteria = example.createCriteria();

        if (specification != null) {
            if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }

        }

        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 规格下拉列表
     * @return
     */
    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

}
