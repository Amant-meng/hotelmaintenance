package com.chinahotelhelp.shm.operational.security;

import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @author Huan.Xia
 * @Title: ShiroRealm
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/03016:43
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserService sysUserService;
    private AuthenticationInfo authenticationInfo;


    /**
     * 权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        if(sysUser.getPremission() != null && sysUser.getPremission().size() > 0){
            authorizationInfo.addStringPermissions(sysUser.getPremission());
        }
        return authorizationInfo;
    }



    /**
     * 授权
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException  {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username  = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        SysUser sysUser = sysUserService.getUserByUsername(username);
        if(sysUser != null){
        ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getPhone());
        Object obj = new SimpleHash("MD5", password, credentialsSalt, 1024);
        String toHex = ((SimpleHash) obj).toHex();
        usernamePasswordToken.setPassword(toHex.toCharArray());
        usernamePasswordToken.setRememberMe(false);

            authenticationInfo = new SimpleAuthenticationInfo(sysUserService.getUserById(sysUser.getId()), sysUser.getPassword(),
                    ByteSource.Util.bytes(sysUser.getPhone()), getName());

        }else{
            throw new AuthenticationException();

        }
        return authenticationInfo;
    }


}