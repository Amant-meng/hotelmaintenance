package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysAnnouncement;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysAnnouncementMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author YangMeng
 * @Title: SysAnnouncementsService
 * @ProjectName merchant-management
 * @Description: 系统通告
 * @date 2019/03/01
 */
@Service
public class SysAnnouncementService {

    @Autowired
    private SysAnnouncementMapper sysAnnouncementMapper;

    /**
     * 查询系统通告
     * @param page
     * @return
     */
    public PageData getSysAnnouncementList(Page page) {
        List<Filed> queryFileds = new ArrayList<>();
        Map<String,Object> params = page.getParams();
        params.put("hi_id", ShiroUtils.getUserEntity().getHiId());
        if(params != null && params.size() > 0){
            //queryFileds.add(new CombFiled( "hi_id",params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("id,hi_id,title,content,createtime,modifytime",
                queryFileds," and deltag='0' order by createtime desc","sys_announcement");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysAnnouncementMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysAnnouncementMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;

    }

    /**
     * 添加系统通告
     * @param sysAnnouncement
     */
    public void addSysAnnouncement(SysAnnouncement sysAnnouncement){
        sysAnnouncement.setHi_id(ShiroUtils.getUserEntity().getHiId());
        sysAnnouncement.setCreatetime(new Date());
        sysAnnouncement.setCreateuser(ShiroUtils.getUserEntity().getTruename());
        sysAnnouncement.setDeltag("0");
        sysAnnouncementMapper.insert(sysAnnouncement);
    }

    /**
     * 根据id获取系统通告
     * @param id
     * @return
     */
    // @CacheEvict(value = "caller", key="#ci_name")
    public SysAnnouncement getSysAnnouncementId(String id){
        Wrapper<SysAnnouncement> sysAnnouncementWrapper = new EntityWrapper<>();
        sysAnnouncementWrapper.eq("id",id);
        List<SysAnnouncement> list = sysAnnouncementMapper.selectList(sysAnnouncementWrapper);
        SysAnnouncement sysAnnouncement = null;
        if(list != null && list.size() > 0){
            sysAnnouncement = list.get(0);
        }
        return sysAnnouncement;
    }

    /**
     * 修改系统通告
     * @param sysAnnouncement
     */
    public void updateSysAnnouncement(SysAnnouncement sysAnnouncement){
        sysAnnouncement.setModifytime(new Date());
        sysAnnouncement.setModifyuser(ShiroUtils.getUserEntity().getTruename());
        sysAnnouncementMapper.updateById(sysAnnouncement);
    }

    /**
     * 通过id删除系统通告
     * @param id
     */
    public void delSysAnnouncement(String id){
        Wrapper<SysAnnouncement> sysAnnouncementWrapper = new EntityWrapper<>();
        sysAnnouncementWrapper.eq("id",id);
        SysAnnouncement sysAnnouncement = new SysAnnouncement();
        sysAnnouncement.setDeltag("1");
        sysAnnouncementMapper.update(sysAnnouncement, sysAnnouncementWrapper);
    }
}
