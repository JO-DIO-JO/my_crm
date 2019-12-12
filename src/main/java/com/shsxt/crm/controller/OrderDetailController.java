package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.OrderDetailsQuery;
import com.shsxt.crm.service.OrderDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("order_detail")
public class OrderDetailController extends BaseController {

    @Resource
    private OrderDetailService orderDetailService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(OrderDetailsQuery orderDetailsQuery,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer rows) {
        orderDetailsQuery.setPageNum(page);
        orderDetailsQuery.setPageSize(rows);
        return orderDetailService.queryByParamsForDataGrid(orderDetailsQuery);
    }
}
