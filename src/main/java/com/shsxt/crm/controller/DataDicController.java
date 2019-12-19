package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.service.DataDicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("data_dic")
public class DataDicController extends BaseController {

    @Resource
    private DataDicService dataDicService;

    @RequestMapping("queryDataDicByDicName")
    @ResponseBody
    public List<Map<String, Object>> queryDataDicByDicName(String dicName) {
        return dataDicService.queryDataDicByDicName(dicName);
    }
}
