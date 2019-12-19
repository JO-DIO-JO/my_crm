package com.shsxt.crm.db.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.vo.DataDic;

import java.util.List;
import java.util.Map;

public interface DataDicMapper extends BaseMapper<DataDic, Integer> {

    List<Map<String, Object>> queryDataDicByDicName(String dicName);

}