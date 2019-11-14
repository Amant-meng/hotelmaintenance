package com.chinahotelhelp.shm.operational.module.log.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinahotelhelp.shm.operational.module.log.config.EsConfig;
import com.chinahotelhelp.shm.operational.module.log.entity.LogData;
import com.chinahotelhelp.shm.operational.module.log.entity.LogParams;
import com.chinahotelhelp.shm.operational.module.log.help.EsClientHelp;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wuyang on 2019/11/5 11:08
 */
@Service
public class LogService {

    @Autowired
    private EsConfig esConfig;

    public Message getTerminalLog(LogParams logParams){
        Message message= Message.N();
        message.setSuccess(false);
        message.setMessage("查询失败");
        if(logParams.getIndexName().equals("")){
            logParams.setIndexName("business");
        }
        String indexName=logParams.getIndexName();
        QueryBuilder condition= QueryBuilders.boolQuery();
        if(!"".equals(logParams.getMonth())){
            if(logParams.getIndexName().equals("business")){
                indexName =indexName+"-*-"+logParams.getMonth();
            }else{
                indexName =indexName+"-"+logParams.getMonth();
            }
        }else{
            indexName +="*";
        }
        if(!EsClientHelp.indicesExists(esConfig.client,indexName)){
            message.setMessage("查询失败,索引不存在");
            return message;
        }
        if(!"".equals(logParams.getTi_id())){
            QueryBuilder queryBuilder=QueryBuilders.matchQuery("fields.ti_id",logParams.getTi_id());
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getHi_name())){
            QueryBuilder queryBuilder=QueryBuilders.matchQuery("fields.ti_name",logParams.getHi_name());
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getLogSTime())){
            QueryBuilder queryBuilder=QueryBuilders.rangeQuery("@timestamp").gte(EsClientHelp.getDateTime(logParams.getLogSTime(),-8)).format("yyyy-MM-dd HH:mm:ss");
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getLogETime())){
            QueryBuilder queryBuilder=QueryBuilders.rangeQuery("@timestamp").lte(EsClientHelp.getDateTime(logParams.getLogETime(),-8)).format("yyyy-MM-dd HH:mm:ss");
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getLogLevel())){
            QueryBuilder queryBuilder=QueryBuilders.matchQuery("message","\t"+logParams.getLogLevel()+"\t");
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getContent())){
            QueryBuilder queryBuilder=QueryBuilders.matchQuery("message",logParams.getContent()).operator(Operator.AND);
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }

        int statrIndex=logParams.getDraw()>1?(logParams.getDraw()-1)*logParams.getLength():0;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(condition);
        searchSourceBuilder.from(statrIndex).size(logParams.getLength()).sort("@timestamp", SortOrder.DESC);
        Map<String,Object> map=EsClientHelp.getData(esConfig.client,indexName,"doc",searchSourceBuilder.toString());
        if(map!=null){
            List<Object> list=(ArrayList)map.get("data");
            if(list!=null && list.size()>0){
                List<LogData> logDatas=new ArrayList<>();
                for (Object obj: list) {
                    JSONObject jobj=(JSONObject)JSON.toJSON(obj);
                    LogData logData=new LogData();
                    logData.setIndexName(jobj.getString("index"));
                    logData.setId(jobj.getString("id"));
                    logData.setTi_id(jobj.getJSONObject("source").getJSONObject("fields").getString("ti_id"));
                    logData.setHi_name(jobj.getJSONObject("source").getJSONObject("fields").getString("ti_name"));
                    logData.setSourceFile(jobj.getJSONObject("source").getString("source"));
                    String datetime=jobj.getJSONObject("source").getString("@timestamp");
                    datetime=datetime.replace("T"," ");
                    datetime=datetime.substring(0,19);
                    datetime=EsClientHelp.getDateTime(datetime,8);
                    logData.setLogTime(datetime);
                    logData.setContent(jobj.getJSONObject("source").getString("message"));
                    logDatas.add(logData);
                    System.out.println(jobj);
                }
                message.setData(logDatas);
            }
        }
        message.setSuccess(true);
        message.setMessage("查询成功");
        return message;
    }

    public Message getTiState(){

        Message message= Message.N();
        message.setSuccess(false);
        message.setMessage("查询失败");
        SearchRequest request=new SearchRequest();
        request.indices("metricbeat*");
        QueryBuilder condition= QueryBuilders.boolQuery();

        // windows.service.name chh-haas-main chh-saas-psb chh-saas-pay chh-saas-main chh-saas-mq
        //	process.name  AmFaceService.exe
        //metricset.name process_summary cpu  memory

        String nowDate=EsClientHelp.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
        String timeStr1=EsClientHelp.getDateTime(nowDate,-8);
        String timeStr2=EsClientHelp.getDateTime1(timeStr1,-2);

        System.out.println("start:"+timeStr2+";end:"+timeStr1);

        QueryBuilder datetime=QueryBuilders.rangeQuery("@timestamp").gte(timeStr2).lte(timeStr1).format("yyyy-MM-dd HH:mm:ss");

        QueryBuilder process= QueryBuilders.termsQuery("process.name","AmFaceService.exe");
        QueryBuilder queryBuilder=QueryBuilders.matchQuery("system.process.state","running");

        QueryBuilder processState=QueryBuilders.boolQuery();
        ((BoolQueryBuilder) processState).must(process).mustNot(queryBuilder);

        QueryBuilder service= QueryBuilders.termsQuery("windows.service.name","chh-saas-psb","chh-haas-main","chh-saas-pay","chh-saas-main","chh-saas-mq");
        QueryBuilder queryBuilder1=QueryBuilders.matchQuery("windows.service.state","Running");

        QueryBuilder serviceState=QueryBuilders.boolQuery();
        ((BoolQueryBuilder) serviceState).must(service).mustNot(queryBuilder1);

        QueryBuilder cpu= QueryBuilders.rangeQuery("system.cpu.total.pct").lt(3.0);
        QueryBuilder cpuState= QueryBuilders.boolQuery();

        QueryBuilder memory= QueryBuilders.rangeQuery("system.memory.used.pct").lt(0.7);
        QueryBuilder memorySate= QueryBuilders.boolQuery();

        ((BoolQueryBuilder) cpuState).must(cpu);
        ((BoolQueryBuilder) memorySate).must(memory);

        ((BoolQueryBuilder) condition).should(processState).should(serviceState).should(cpuState).should(memorySate);

        BoolQueryBuilder queryEs = QueryBuilders.boolQuery();
        queryEs.must(condition).must(datetime);

        TermsAggregationBuilder groupTerms = AggregationBuilders.terms("errorTiid").field("fields.ti_id");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryEs).aggregation(groupTerms);

        List<String> errorList=EsClientHelp.getTiId(esConfig.client,"metricbeat*","doc",searchSourceBuilder.toString());
        List<String> allList=getAllTi();
        Map<String,String> tiMap=new HashMap<>();

        if(allList!=null){
          if(errorList!=null){
              allList.removeAll(errorList);
              tiMap.put("error","'"+String.join("','", errorList)+"'");
              tiMap.put("all","'"+String.join("','", allList)+"'");
          }else{
              tiMap.put("error","'1'");
              tiMap.put("all","'"+String.join("','", allList)+"'");
          }
        }else{
            tiMap.put("error","'1'");
            tiMap.put("all","'1'");
        }
        message.setSuccess(true);
        message.setMessage("查询成功");
        message.setData(tiMap);
        return message;
    }


    private List<String> getAllTi(){
        String nowDate=EsClientHelp.DateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
        String timeStr1=EsClientHelp.getDateTime(nowDate,-8);
        String timeStr2=EsClientHelp.getDateTime1(timeStr1,-5);

        System.out.println("start:"+timeStr2+";end:"+timeStr1);
        QueryBuilder datetime=QueryBuilders.rangeQuery("@timestamp").gte(timeStr2).lte(timeStr1).format("yyyy-MM-dd HH:mm:ss");

        BoolQueryBuilder queryEs = QueryBuilders.boolQuery();
        queryEs.must(datetime);

        TermsAggregationBuilder groupTerms = AggregationBuilders.terms("errorTiid").field("fields.ti_id");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryEs).aggregation(groupTerms);

        List<String> list=EsClientHelp.getTiId(esConfig.client,"metricbeat*","doc",searchSourceBuilder.toString());
        return list;
    }

    public Message getTiInfo(LogParams logParams){
        Message message= Message.N();
        message.setSuccess(false);
        message.setMessage("查询失败");

        String indexName="";
        QueryBuilder condition= QueryBuilders.boolQuery();
        if(!"".equals(logParams.getMonth())){
            indexName ="metricbeat-"+logParams.getMonth();
        }else{
            indexName ="metricbeat*";
        }
        if(!EsClientHelp.indicesExists(esConfig.client,indexName)){
            message.setMessage("查询失败,索引不存在");
            return message;
        }
        if(!"".equals(logParams.getTi_id())){
            QueryBuilder queryBuilder=QueryBuilders.matchQuery("fields.ti_id",logParams.getTi_id());
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getLogSTime())){
            QueryBuilder queryBuilder=QueryBuilders.rangeQuery("@timestamp").gte(EsClientHelp.getDateTime(logParams.getLogSTime(),-8)).format("yyyy-MM-dd HH:mm:ss");
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        if(!"".equals(logParams.getLogETime())){
            QueryBuilder queryBuilder=QueryBuilders.rangeQuery("@timestamp").lte(EsClientHelp.getDateTime(logParams.getLogETime(),-8)).format("yyyy-MM-dd HH:mm:ss");
            ((BoolQueryBuilder) condition).must(queryBuilder);
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0).size(20).sort("@timestamp", SortOrder.DESC);
        Map<String,Object> map=EsClientHelp.getData(esConfig.client,indexName,"doc",searchSourceBuilder.toString());
        if(map!=null){
            List<Object> list=(ArrayList)map.get("data");
            if(list!=null && list.size()>0){
                List<LogData> logDatas=new ArrayList<>();
                for (Object obj: list) {
                    JSONObject jobj=(JSONObject)JSON.toJSON(obj);
                    LogData logData=new LogData();
                    logData.setIndexName(jobj.getString("index"));
                    logData.setId(jobj.getString("id"));
                    logData.setTi_id(jobj.getJSONObject("source").getJSONObject("fields").getString("ti_id"));
                    logData.setHi_name(jobj.getJSONObject("source").getJSONObject("fields").getString("ti_name"));
                    logData.setSourceFile(jobj.getJSONObject("source").getString("source"));
                    String datetime=jobj.getJSONObject("source").getString("@timestamp");
                    datetime=datetime.replace("T"," ");
                    datetime=datetime.substring(0,19);
                    datetime=EsClientHelp.getDateTime(datetime,8);
                    logData.setLogTime(datetime);
                    logData.setContent(jobj.getJSONObject("source").getString("message"));
                    logDatas.add(logData);
                    System.out.println(jobj);
                }
                message.setData(logDatas);
            }
        }
        message.setSuccess(true);
        message.setMessage("查询成功");
        return message;
    }
}
