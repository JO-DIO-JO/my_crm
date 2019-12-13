package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.CustomerReprieveQuery;
import com.shsxt.crm.service.CustomerReprieveService;
import com.shsxt.crm.vo.CustomerReprieve;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_repr")
public class CustomerReprieveController extends BaseController {

    @Resource
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerReprieveByParams(CustomerReprieveQuery customerReprieveQuery,
                                                             @RequestParam(defaultValue = "1") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer rows) {
        customerReprieveQuery.setPageNum(page);
        customerReprieveQuery.setPageSize(rows);
        return customerReprieveService.queryByParamsForDataGrid(customerReprieveQuery);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(CustomerReprieve customerReprieve){
        customerReprieveService.saveCustomerReprieve(customerReprieve);
        return success("措施添加成功!");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(CustomerReprieve customerReprieve){
        customerReprieveService.updateCustomerReprieve(customerReprieve);
        return success("措施更新成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id){
        customerReprieveService.deleteCustomerReprieve(id);
        return success("措施删除成功!");
    }

}
