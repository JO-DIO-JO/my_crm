package com.shsxt.crm.service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.db.dao.DataDicMapper;
import com.shsxt.crm.vo.DataDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataDicService extends BaseService<DataDic, Integer> {

    @Autowired
    private DataDicMapper dataDicMapper;

    public List<Map<String, Object>> queryDataDicByDicName(String dicName) {
        return dataDicMapper.queryDataDicByDicName(dicName);
    }
}
