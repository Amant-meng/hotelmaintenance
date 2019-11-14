package com.chinahotelhelp.shm.operational.config;


import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysUserMapper;
import com.chinahotelhelp.shm.operational.module.sys.service.SysUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * @Auther: 杨昌亮
 * @Date: 2019/1/3 17:11
 * @Description: 验证器，增加了登录次数校验功能
 */
@Component
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    private int maxRetryNum;
    private static Cache<String, AtomicInteger> passwordRetryCache;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;

    public void setMaxRetryNum(int maxRetryNum) {
        this.maxRetryNum = maxRetryNum;
    }

    public RetryLimitHashedCredentialsMatcher(EhCacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo info) {
        //获取用户名
        String username = (String)authenticationToken.getPrincipal();
        Object credentials = authenticationToken.getCredentials();
        //获取用户登录次数
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            //如果用户没有登陆过,登陆次数加1 并放入缓存
            retryCount = new AtomicInteger(1);
            passwordRetryCache.put(username, retryCount);
        }
        SysUser userByUsername = sysUserService.getUserByUsername(username);
        if (retryCount.incrementAndGet() > maxRetryNum) {
            userByUsername.setEnabled("0");
            userByUsername.setEnabled_name("否");
            sysUserMapper.updateById(userByUsername);
            log.warn("用户[{}]进行登录验证..失败验证超过{}次,账户已锁定", username, maxRetryNum);
            throw new ExcessiveAttemptsException("username: " + username + " 短期内尝试登录密码或用户名错误三次，账户已锁定！");
        }
        boolean matches = super.doCredentialsMatch(authenticationToken, info);
        if (matches) {
            //clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }
    /**
     * 根据用户名解锁用户
     * @param username
     * @return
     */
    public Message unlockAccount(String username){
        Message message = Message.N();
        try {
            SysUser user = sysUserService.getUserByUsername(username);
            if (user != null){
                //修改数据库的锁定字段状态
                user.setEnabled("1");
                user.setEnabled_name("是");
                sysUserMapper.updateById(user);
                passwordRetryCache.remove(username);
                message.setSuccess(true);
                message.setMessage("解锁成功！");
            }
        } catch (CacheException e) {
            e.printStackTrace();
            message.setMessage("解锁失败！");
        }
        return message;
    }

}