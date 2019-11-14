package com.chinahotelhelp.shm.operational.config;

import com.chinahotelhelp.shm.operational.filter.ShiroLoginFilter;
import com.chinahotelhelp.shm.operational.security.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: ShiroConfig
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/0309:59
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/main.html");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/batch/**", "anon");
        filterChainDefinitionMap.put("/dict/**", "anon");
        filterChainDefinitionMap.put("/registrationOrder/**", "anon");
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/dist/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/imageValidator", "anon");
        filterChainDefinitionMap.put("/caller/addcaller", "anon");
        filterChainDefinitionMap.put("/report/**", "anon");
        filterChainDefinitionMap.put("/pmsInfo/**", "anon");
        filterChainDefinitionMap.put("/teammember/**", "anon");
        filterChainDefinitionMap.put("/lead/**", "anon");
        filterChainDefinitionMap.put("/orderservice/**", "anon");
        filterChainDefinitionMap.put("/mainteam/**", "anon");
        filterChainDefinitionMap.put("/merchant/**", "anon");
        filterChainDefinitionMap.put("/roomimg/**", "anon");
        filterChainDefinitionMap.put("/hotelLogo/**", "anon");
        filterChainDefinitionMap.put("/cfTiBloc/**", "anon");
        filterChainDefinitionMap.put("/cfTiHotel/**", "anon");
        filterChainDefinitionMap.put("/cfTiTerminal/**", "anon");
        filterChainDefinitionMap.put("/cfTiMainVersion/**", "anon");
        filterChainDefinitionMap.put("/cfTiLock/**", "anon");

        filterChainDefinitionMap.put("/template/**", "anon");
        filterChainDefinitionMap.put("/parasStructure/**", "anon");
        filterChainDefinitionMap.put("/terminalLog/**", "anon");
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //filterChainDefinitionMap.put("/logout", "logout");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        Map<String, Filter> filters=new LinkedHashMap<>();
        //filters.put("authc", new ShiroLoginFilter());
        filters.put("authc", new ShiroLoginFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    @Bean("shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")//可选
    public ShiroRealm shiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setAuthorizationCachingEnabled(false);
        //shiroRealm.setCredentialsMatcher(matcher);
        shiroRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
        return shiroRealm;
    }


    /**
     * 定义安全管理器securityManager,注入自定义的realm
     * @param shiroRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
        return manager;
    }

    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 配置shiro跟spring的关联
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * lifecycleBeanPostProcessor是负责生命周期的 , 初始化和销毁的类
     * (可选)
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }



    /**
     * 缓存管理器
     * @return cacheManager
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }
    /**
     * 配置密码比较器
     * @return
     */
    @Bean("credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(){
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
        retryLimitHashedCredentialsMatcher.setMaxRetryNum(3);
        return retryLimitHashedCredentialsMatcher;
    }

}
