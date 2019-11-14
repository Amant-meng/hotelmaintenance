package com.chinahotelhelp.shm.operational.module.log.help;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import io.searchbox.indices.IndicesExists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wuyang on 2019/11/5 10:54
 */

public class EsClientHelp {


    public static boolean indicesExists(JestClient client,String indexName) {
        IndicesExists indicesExists = new IndicesExists.Builder(indexName).build();
        JestResult result = null ;
        try {
            result = client.execute(indicesExists);
            return result.isSucceeded() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 时间减8小时
     * @param stringDate
     * @return
     */
    public static String getDateTime(String stringDate,int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(StringToDate(stringDate,"yyyy-MM-dd HH:mm:ss"));
        cal.add(Calendar.HOUR, hours);
        Date date = cal.getTime();
        return DateToString(date,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateTime1(String stringDate,int minute){
        Calendar cal = Calendar.getInstance();
        cal.setTime(StringToDate(stringDate,"yyyy-MM-dd HH:mm:ss"));
        cal.add(Calendar.MINUTE, minute);
        Date date = cal.getTime();
        return DateToString(date,"yyyy-MM-dd HH:mm:ss");
    }
    public static Date StringToDate(String timeStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }


    public static Map getData(JestClient jestClient, String indexName, String typeName, String query) {
        try{
            Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
            System.out.println("搜索语句:"+query);
            JestResult jr = jestClient.execute(search);
            Object object=new Object();
            Object object1= ((SearchResult) jr).getHits(object.getClass());
            if(object1!=null && ((List) object1).size()>0)
            {
                List<Object> list=(List) object1;
                Map<String,Object> map=new HashMap<>();
                map.put("count",((SearchResult) jr).getTotal());
                map.put("data",list);
                return map;
            }
        }catch (Exception ex){}
        return null;
    }

    public static List<String> getTiId(JestClient jestClient, String indexName, String typeName, String query) {
        try{
            Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
            System.out.println("搜索语句:"+query);
            SearchResult jr = jestClient.execute(search);
            Object object=new Object();
            MetricAggregation aggregation= jr.getAggregations();
            TermsAggregation termsAggregation= aggregation.getTermsAggregation("errorTiid");
            List<String> list=new ArrayList<>();
            for (Object obj: termsAggregation.getBuckets()) {
                JSONObject jsonObject=(JSONObject)JSON.toJSON(obj);
                list.add(jsonObject.getString("key"));
            }
            return  list;
        }catch (Exception ex){}
        return null;
    }
}
