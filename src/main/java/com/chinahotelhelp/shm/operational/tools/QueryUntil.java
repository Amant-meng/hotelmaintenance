package com.chinahotelhelp.shm.operational.tools;

import com.chinahotelhelp.shm.operational.common.filed.Filed;

import java.util.List;

/**
 * @author Huan.Xia
 * @Title: QueryUntil
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01413:53
 */
public class QueryUntil {
    /**
     * 获取查询语句
     * @param select
     * @param fileds
     * @param cond
     * @param tablename
     * @return
     */
    public static String[] getQuerySql(String select, List<Filed> fileds, String cond, String tablename){
        return getQuerySql(select,fileds,cond,tablename, true);
    }


    /**
     *
     * @param select
     * @param fileds
     * @param cond
     * @param tablename
     * @return
     */
    public static String[] getQuerySql(String select, List<Filed> fileds, String cond, String tablename, Boolean appendLimit){
        StringBuffer sb = new StringBuffer();
        StringBuffer querySb = new StringBuffer();
        StringBuffer queryCountSb = new StringBuffer();
        querySb.append("select * from (select " + select + " from " + tablename + " where 1= 1");
        if(fileds != null && fileds.size() > 0){
            for (Filed filed:
                    fileds) {
                sb.append(filed.getQuery());
            }
        }
        sb.append(cond);
        queryCountSb.append("select count(1) as count from " + tablename);
        queryCountSb.append(" where 1=1 ");
        querySb.append(sb.toString());
        queryCountSb.append(sb.toString());
        querySb.append(" ) t");
        if(appendLimit){
            querySb.append(" limit  #{start},#{length}");
        }
        return new String[]{querySb.toString(), queryCountSb.toString()};
    }

    /**
     *
     * @param select
     * @param fileds
     * @param cond
     * @param tablename
     * @return
     */
    public static String[] getReportQuerySql(String select, List<Filed> fileds, String cond, String tablename, Boolean appendLimit){
        StringBuffer sb = new StringBuffer();
        StringBuffer querySb = new StringBuffer();
        StringBuffer queryCountSb = new StringBuffer();
        querySb.append("select * from (select " + "CAST(@rownum := @rownum+1 AS CHAR) AS xu_id,q."+ select + " from " + tablename + " where 1= 1");
        if(fileds != null && fileds.size() > 0){
            for (Filed filed:
                    fileds) {
                sb.append(filed.getQuery());
            }
        }
        sb.append(cond);
        queryCountSb.append("select count(1) as count from " + tablename);
        queryCountSb.append(" where 1=1 ");
        querySb.append(sb.toString());
        queryCountSb.append(sb.toString());
        querySb.append(" ) t");
        if(appendLimit){
            querySb.append(" limit  #{start},#{length}");
        }
        return new String[]{querySb.toString(), queryCountSb.toString()};
    }


    /**
     * 获取查询统计语句
     * @param select
     * @param fileds
     * @param tablename
     * @return
     */
    public static String getCountQuerySql(String select, List<Filed> fileds, String tablename){
        StringBuffer sb = new StringBuffer();
        StringBuffer querySb = new StringBuffer();
        querySb.append("select " + select + " from " + tablename + " where 1= 1");
        if(fileds != null && fileds.size() > 0){
            for (Filed filed:
                    fileds) {
                sb.append(filed.getQuery());
            }
        }
        querySb.append(sb.toString());
        return querySb.toString();
    }
}
