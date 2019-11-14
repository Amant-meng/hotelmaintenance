package com.chinahotelhelp.shm.operational.module.terminal.entity;

/**
 * @Classname RemoteDTO
 * @Description TODO
 * @Date 2019/11/8 10:14
 * @Created by Changliang.yang
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoteDTO {
    private Integer status;
    /**
     * 操作类型 1.系统层：关机、重启 2.windows服务层：启动、停止 3.应用层：启动、停止 4.调用接口
     */
    private String type;

    /**
     * 操作指令
     */
    private String cmd;

    /**
     * 接口调用地址
     */
    private String url;

    /**
     * post接口发送的参数
     */
    private String json;
    private String hotelId;
    private String userId;
    private String timeStamp;

}
