package com.chinahotelhelp.shm.operational.module.hotel.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiHotel;
import com.chinahotelhelp.shm.operational.module.hotel.service.CfTiHotelService;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiHotelController
 *   @Description: 酒店信息接口
 *   @date 2019/10/23
*/

@Api(description = "酒店信息接口")
@RequestMapping("/cfTiHotel")
@RestController
public class CfTiHotelController extends BaseController {

    @Autowired
    private CfTiHotelService cfTiHotelService;

    @AnnotationLog(value = "获取酒店列表")
    @ApiOperation(value = "获取酒店列表", notes = "分页获取酒店列表")
    @RequestMapping(value = "getHotelList",method = RequestMethod.GET)
    public PageData getHotelList(Page page){

        return cfTiHotelService.getHotelList(page);
    }

    @AnnotationLog(value = "删除酒店信息")
    @ApiOperation(value = "删除酒店信息", notes = "删除酒店信息")
    @RequestMapping(value = "delHotel/{hi_id}",method = RequestMethod.DELETE)
    public Message delHotel(@PathVariable String hi_id){
        cfTiHotelService.delHotel(hi_id);
        return Message.success();
    }

    @AnnotationLog(value = "更新酒店信息")
    @ApiOperation(value = "更新酒店信息", notes = "更新酒店信息")
    @RequestMapping(value = "updateHotel",method = RequestMethod.POST)
    public Message updateHotel(@RequestBody CfTiHotel cfTiHotel){
        cfTiHotelService.updateHotel(cfTiHotel);
        return Message.success();
    }

    @AnnotationLog(value = "通过酒店id查询酒店信息")
    @ApiOperation(value = "通过酒店id查询酒店信息", notes = "通过酒店id查询酒店信息")
    @RequestMapping(value = "getHotelInfoById/{hi_id}",method = RequestMethod.GET)
    public Message getHotelInfoById(@PathVariable String hi_id){

        CfTiHotel cfTiHotel = cfTiHotelService.getHotelInfoById(hi_id);
        return Message.success(cfTiHotel);
    }

    @AnnotationLog(value = "添加酒店信息")
    @ApiOperation(value = "添加酒店信息", notes = "添加酒店信息")
    @RequestMapping(value = "addHotel",method = RequestMethod.POST)
    public Message addHotel(@RequestBody CfTiHotel cfTiHotel){
        return cfTiHotelService.addHotel(cfTiHotel);
    }

    @AnnotationLog(value = "通过集团id获取酒店列表")
    @ApiOperation(value = "通过集团id获取酒店列表", notes = "通过集团id获取酒店列表")
    @RequestMapping(value = "getHotelListByBolcId/{bloc_id}",method = RequestMethod.GET)
    public Message getHotelListByBolcId(@PathVariable String bloc_id){
        List<CfTiHotel> cfTiHotelList = cfTiHotelService.getHotelListByBolcId(bloc_id);
        return Message.success(cfTiHotelList);
    }
}
