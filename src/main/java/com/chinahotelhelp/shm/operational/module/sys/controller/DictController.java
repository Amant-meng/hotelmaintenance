package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysDict;
import com.chinahotelhelp.shm.operational.module.sys.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: DictController
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01417:35
 */
@Api(description = "字典表接口")
@RequestMapping("/dict")
@RestController
public class DictController extends BaseController {
    @Autowired
    @Lazy
    private SysDictService sysDictService;

    /**
     * 根据TYPE groupby排序查询所有数据
     */
    @ApiOperation(value = "根据TYPE groupby排序查询所有数据", notes = "根据TYPE groupby排序查询所有数据")
    @RequestMapping(value = "getInfo",method = RequestMethod.GET)
    @ResponseBody
    public Message getInfo() {
        return Message.success(sysDictService.info());
    }
    /**
     * 根据TYPE获取单项字典表详细数据
     *
     * @param TYPE
     * @return
     */
    @ApiOperation(value = "根据TYPE获取单项字典表详细数据", notes = "根据TYPE获取单项字典表详细数据")
    @RequestMapping(value = "info/{TYPE}",method = RequestMethod.GET)
    @ResponseBody
    public Message info(@PathVariable("TYPE") String TYPE) {
        return Message.success(sysDictService.getInfo(TYPE));
    }
    /**
     * 获取多项字典表
     *
     * @param TYPE
     * @return
     */
    @ApiOperation(value = "获取多项字典表", notes = "获取多项字典表数据")
    @RequestMapping(value = "more/{TYPE}",method = RequestMethod.GET)
    @ResponseBody
    public Message more(@PathVariable("TYPE") String TYPE) {
        Map<String, Object> result = new HashMap<>();
        if (TYPE != null && TYPE.length() > 0) {
            String[] typeArray = TYPE.split(",");
            for (String item :
                    typeArray) {
                result.put(item, sysDictService.getInfo(item));
            }
        }
        return Message.success(result);
    }
    /**
     * 分页获取所有字典表数据
     */
    @ApiOperation(value = "分页获取所有字典表数据", notes = "分页获取所有字典表数据")
    @RequestMapping(value = "allDict",method = RequestMethod.GET)
    @ResponseBody
    public PageData Dict(Page page) {

        return  sysDictService.getDict(page);
    }


    /**
     * 根据VALUE获取字典表数据
     */
    @ApiOperation(value = "根据VALUE获取字典表数据", notes = "根据VALUE获取字典表数据")
    @RequestMapping(value = "getDict/{VALUE}",method = RequestMethod.GET)
    @ResponseBody
    public Message getDict(@PathVariable String VALUE) {
        List<SysDict> result = sysDictService.getPcodeDict(VALUE);
        return Message.success(result);
    }
    /**
     * 根据id删除字典表数据
     */
    @ApiOperation(value = "根据ID删除字典表数据", notes = "根据ID删除字典表数据")
    @RequestMapping(value = "delDict/{ID}",method = RequestMethod.DELETE)
    public Message delDict(@PathVariable String ID) {

        return sysDictService.delPcodeDict(ID);
    }
    /**
     * 根据id获取字典表数据详细信息
     */
    @ApiOperation(value = "根据id获取字典表数据详细信息", notes = "根据id获取字典表数据详细信息")
    @RequestMapping(value = "getDictInfo/{ID}",method = RequestMethod.GET)
    public Message getDictInfo(@PathVariable Integer ID) {

        return sysDictService.getDictInfo(ID);
    }

    /**
     * 根据id获取字典表数据子集详细信息
     */
    @ApiOperation(value = "根据ID获取字典表数据子集详细信息", notes = "根据ID获取字典表数据子集详细信息")
    @RequestMapping(value = "getChildInfo/{value}",method = RequestMethod.GET)
    public Message getChildInfo(@PathVariable String value) {

        return sysDictService.getChildInfo(value);
    }
    /**
     * 添加字典表数据
     */
    @ApiOperation(value = "添加字典表数据", notes = "添加字典表数据")
    @RequestMapping(value = "addDict",method = RequestMethod.POST)
    public Message addDict(@RequestBody SysDict sysDict) {

        return sysDictService.addPcodeDict(sysDict);
    }
    /**
     * 修改字典表数据
     */
    @ApiOperation(value = "修改字典表数据", notes = "修改字典表数据")
    @RequestMapping(value = "updateDict",method = RequestMethod.POST)
    public Message updateDict(@RequestBody SysDict sysDict) {

        return sysDictService.updateDict(sysDict);
    }

    /**
     * 获取所有的name和type的拼接
     */
    @ApiOperation(value = "获取所有的name和type的拼接", notes = "获取所有的name和type的拼接")
    @RequestMapping(value = "getConcat/{id}",method = RequestMethod.GET)
    public Message getConcat(@PathVariable Integer id) {

        return sysDictService.getConcat(id);
    }

    /**
     * 获取所有去重type
     */
    @ApiOperation(value = "获取所有去重type", notes = "获取所有去重type")
    @RequestMapping(value = "getType",method = RequestMethod.GET)
    public Message getType(){
        return sysDictService.getType();
    }
}
