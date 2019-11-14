package com.chinahotelhelp.shm.operational.module.sys.controller;


import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.config.KaptchaConfig;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.service.SysUserService;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: RestController
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/03010:43
 */
@Api(description = "主页main接口")
@RestController
public class HomeController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;


    /**
     * 登录
     *
     * @param sysUser
     * @return
     */
    @AnnotationLog(value = "登录")
    @ApiOperation(value = "登录", notes = "登录接口")
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Message login(@RequestBody SysUser sysUser) {
        System.out.println("登录从session获取到的验证码："+ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY));
        String sessionID = ShiroUtils.getSession().getId()+"";
        System.out.println("登录获取到的sessionID:"+sessionID);
        return sysUserService.login(sysUser);
    }
    @ApiOperation(value = "退出", notes = "退出接口")
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ResponseBody
    public Message logout() {
        ShiroUtils.logout();
        Message message = Message.N();
        message.setSuccess(true);
        return message;
    }

    /**
     * 主框架
     * @return
     */
    @AnnotationLog(value = "主框架接口，设置登录超时、获取当前登录用户信息")
    @ApiOperation(value = "主框架", notes = "主框架接口，设置登录超时、获取当前登录用户信息")
    @RequestMapping(value = "main",method = RequestMethod.GET)
    @ResponseBody
    public Message main() {
        long timeout = SecurityUtils.getSubject().getSession().getTimeout();
        return Message.success( (SysUser) SecurityUtils.getSubject().getPrincipal());
    }

    /**
     * 校验用户名是已占用
     * @param username
     * @return
     */
    @AnnotationLog(value = "校验用户名是已占用")
    @ApiOperation(value = "校验用户名", notes = "校验用户名是否被占用")
    @RequestMapping(value = "check",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> check(@RequestParam String username) {
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        result.put("valid", sysUserService.getUserByUsername(username) == null);
        return result;
    }

    /**
     * 图片验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @ApiOperation(value = "验证码", notes = "图片验证码校验")
    @RequestMapping(value = "imageValidator",method = RequestMethod.GET)
    public void imageValidator(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {

            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, createText);
            System.out.println("******************验证码是: " + createText + "******************");
            String sessionID = httpServletRequest.getSession().getId()+"";
            System.out.println("获取到的验证码ID："+sessionID);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setContentType("image/jpeg");

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();


    }

}
