package com.chinahotelhelp.shm.operational.module.template.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.template.entity.Template;
import com.chinahotelhelp.shm.operational.module.template.entity.TemplateParas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wuyang on 2019/10/23 11:12
 */
@Mapper
@Repository
public interface TemplateMapper extends BaseMapper<Template> {

    int addTemplate(Template template);

    int modifyTemplate(Template template);

    int deleteTemplate(String temp_id);

    List<Map<String,String>> getTemplate(String temp_name);

    int addTemplateParas(TemplateParas templateParas);

    int deleteTemplateParas(String temp_id);

    List<Map> execSQL(Page page);
}
