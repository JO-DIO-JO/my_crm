package com.shsxt.crm.service;

import com.github.pagehelper.PageInfo;
import com.shsxt.base.BaseService;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class SaleChangeService extends BaseService<SaleChance, Integer> {

    private Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号

    /**
     * 多条件、分页查询
     *
     * @param saleChanceQuery
     * @return
     */
    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        PageInfo<SaleChance> pageInfo = queryForPage(saleChanceQuery);
        Map<String, Object> result = new HashMap<>();
        result.put("total", pageInfo.getTotal());
        result.put("rows", pageInfo.getList());
        return result;
    }

    /**
     * 创建营销机会记录
     * <p>
     * 1.参数合法校验
     * 客户名  联系人  联系方式 非空校验
     * 2.分配时间  分配状态  分配人
     * 分配人默认  null  分配时间 默认 null  分配状态 默认值 0-未分配
     * 如果指定分配人 设置分配时间 分配状态 1-已分配
     * 3.开发结果  0-未开发 1-开发中 2-开发成功 3-开发失败
     * 添加时 默认 未开发
     * 如果指定了分配人  此时为开发中
     * 4. is_valid=1 createDate  updateDate
     */
    public void saveSaleChance(SaleChance saleChance) {
        // 参数合法校验
        checkSaleChanceParams(saleChance);
        // 分配状态 默认值 0-未分配   开发结果 默认 0-未开发
        saleChance.setState(0);
        saleChance.setDevResult(0);
        if (StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setCreateDate(new Date());
            saleChance.setState(1);
            saleChance.setDevResult(1);
        }
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(save(saleChance) < 1, "营销机会数据添加失败!");
    }

    /**
     * 修改营销机会记录
     * <p>
     * 1. 参数合法校验
     * 客户名  联系人  联系方式 非空校验
     * 更新记录存在性校验
     * 2. 分配人
     * 更新时 没有改动分配人 不做处理
     * 如果添加时没有设置分配人 更新时设置分配人
     * 设置分配时间分配状态
     * 如果添加时 设置分配人   更新时清空分配人
     * 分配时间  分配状态
     * 3. 开发状态
     * 默认未开发
     * 如果设置分配人  开发中
     * 4. updateDate
     */
    public void updateSaleChance(SaleChance saleChance) {
        // 参数合法校验
        checkSaleChanceParams(saleChance);
        Integer sid = saleChance.getId();
        SaleChance tempsaleChance = queryById(sid);
        AssertUtil.isTrue(null == sid || null == tempsaleChance, "待更新记录不存在!");
        // 分配人判断
        if (StringUtils.isBlank(tempsaleChance.getAssignMan()) && StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }
        if (StringUtils.isNotBlank(tempsaleChance.getAssignMan()) && StringUtils.isBlank(saleChance.getAssignMan())) {
            saleChance.setState(0);
            saleChance.setAssignTime(null);
        }
        // 开发状态  默认未开发
        saleChance.setDevResult(0);
        if (StringUtils.isNotBlank(saleChance.getAssignMan())) {
            saleChance.setDevResult(1);
        }
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(update(saleChance) < 1, "营销机会数据更新失败!");
    }

    /**
     * 删除营销机会记录
     *
     * @param ids
     */
    public void deleteSaleChanceBatch(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的记录!");
        AssertUtil.isTrue(deleteBatch(ids) != ids.length, "记录删除失败!");
    }

    private void checkSaleChanceParams(SaleChance saleChance) {
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()), "请输入客户名称!");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()), "请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()), "手机号不能为空!");
        AssertUtil.isTrue(!(p.matcher(saleChance.getLinkPhone()).matches()), "手机号格式不合法!");
    }

    public void updateSaleChanceDevResult(Integer devResult, Integer sid) {
        AssertUtil.isTrue(null == devResult, "机会数据开发状态异常!");
        SaleChance saleChance = queryById(sid);
        AssertUtil.isTrue(null == saleChance, "待更新的机会数据不存在!");
        saleChance.setDevResult(devResult);
        AssertUtil.isTrue(update(saleChance) < 1, "机会数据开发状态更新失败!");
    }

}






