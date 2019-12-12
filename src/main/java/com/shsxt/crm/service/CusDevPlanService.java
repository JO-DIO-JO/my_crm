package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.CusDevPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    /**
     * 添加计划项
     *
     * @param cusDevPlan
     */
    public void saveCusDevPlan(CusDevPlan cusDevPlan) {
        checkParams(cusDevPlan);
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(save(cusDevPlan) < 1, "计划项数据添加失败");
    }

    /**
     * 更新计划项
     *
     * @param cusDevPlan
     */
    public void updateCusDevPlan(CusDevPlan cusDevPlan) {
        checkParams(cusDevPlan);
        AssertUtil.isTrue(null == cusDevPlan.getId() || null == queryById(cusDevPlan.getId()), "待更新记录不存在");
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(update(cusDevPlan) < 1, "计划项数据更新失败");
    }

    /**
     * 删除计划项
     *
     * @param ids
     */
    public void deleteCusDevPlan(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的记录!");
        AssertUtil.isTrue(deleteBatch(ids) != ids.length, "记录删除失败!");
    }
    /**
     * 参数校验
     *
     * @param cusDevPlan
     */
    private void checkParams(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(null == cusDevPlan.getPlanDate(), "请指定计划项时间");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()), "请输入计划项内容");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getExeAffect()), "请输入执行效果");
    }
}
