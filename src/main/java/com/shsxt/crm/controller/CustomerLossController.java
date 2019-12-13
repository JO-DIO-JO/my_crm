package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {

    @Resource
    private CustomerLossService customerLossService;

    @Resource
    private CustomerService customerService;

    @RequestMapping("index")
    public String index() {
        return "customer_loss";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows) {
        customerLossQuery.setPageNum(page);
        customerLossQuery.setPageSize(rows);
        return customerLossService.queryByParamsForDataGrid(customerLossQuery);
    }

    @RequestMapping("openRepr")
    public String openRepr(String cusNo,Integer lossId, Model model) {
        Customer customer = customerService.queryCustomerByCusNo(cusNo);
        model.addAttribute("customer", customer);
        model.addAttribute("lossId", lossId);
        return "customer_loss_repr";
    }

}
