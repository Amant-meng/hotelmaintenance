package com.chinahotelhelp.shm.operational.module.log.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wuyang on 2019/11/5 11:31
 */
@Configuration
public class EsConfig {

    public JestClient client;

    @Bean
    public void getClientConfig(){
        try{
            JestClientFactory factory = new JestClientFactory();
            factory.setHttpClientConfig(new HttpClientConfig
                    .Builder("http://139.159.143.103:9200")
                    // .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                    .multiThreaded(true)
                    .readTimeout(100000)
                    .build());
            client = factory.getObject();
            //return client ;
        }catch (Exception ex){}
        //return null;
    }
}
