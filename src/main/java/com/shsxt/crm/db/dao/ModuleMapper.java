package com.shsxt.crm.db.dao;

import com.shsxt.base.BaseMapper;
import com.shsxt.crm.model.TreeDto;
import com.shsxt.crm.vo.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ModuleMapper extends BaseMapper<Module, Integer> {

    List<Map<String,Object>> queryAllModules();

    List<TreeDto> queryAllModules02();

    Module queryModuleByGradeAndModuleName(@Param("grade") Integer grade, @Param("moduleName") String moduleName);

    Module queryModuleByOptValue(@Param("optValue") String optValue);

    Integer countSubModulesByMid(@Param("mid") Integer mid);

}