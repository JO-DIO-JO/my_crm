package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.annotations.RequirePermission;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChangeService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChangeController extends BaseController {

    @Resource
    private SaleChangeService saleChangeService;

    @Resource
    private UserService userService;

    /**
     * 转发到营销机会管理选项卡页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "sale_chance";
    }

    /**
     * 1.展示选项卡数据
     * 2.分页查询
     * 3.多条件查询
     *
     * @param saleChanceQuery
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery,
                                                        @RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        @RequestParam(defaultValue = "0") Integer flag,
                                                        HttpServletRequest request) {
        saleChanceQuery.setPageNum(page);
        saleChanceQuery.setPageSize(rows);
        if (flag == 1) {
            // 设置分配人
            saleChanceQuery.setAssignMan(LoginUserUtil.releaseUserIdFromCookie(request));
        }
        Map<String, Object> result = saleChangeService.querySaleChancesByParams(saleChanceQuery);
        return result;
    }

    /**
     * 创建营销机会记录
     *
     * @param request
     * @param saleChance
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    @RequirePermission(aclValue = "101002")
    public ResultInfo saveSaleChance(HttpServletRequest request, SaleChance saleChance) {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        saleChance.setCreateMan(userService.queryById(userId).getTrueName());
        saleChangeService.saveSaleChance(saleChance);
        return success("营销机会数据添加成功!");
    }

    /**
     * 修改营销机会记录
     *
     * @param saleChance
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    @RequirePermission(aclValue = "101004")
    public ResultInfo updateSaleChance(SaleChance saleChance) {
        saleChangeService.updateSaleChance(saleChance);
        return success("营销机会数据更新成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequirePermission(aclValue = "101003")
    public ResultInfo deleteSaleChanceBatch(Integer[] ids) {
        saleChangeService.deleteSaleChanceBatch(ids);
        return success("营销机会数据删除成功!");
    }

    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(Integer devResult,Integer sid){
        saleChangeService.updateSaleChanceDevResult(devResult,sid);
        return success("营销机会开发状态更新成功");
    }
}






