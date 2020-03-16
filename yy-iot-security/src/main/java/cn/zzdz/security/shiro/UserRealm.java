package cn.zzdz.security.shiro;

import cn.zzdz.common.entity.security.ProfileResult;
import cn.zzdz.common.entity.security.SysUser;
import cn.zzdz.security.dao.SysUserDao;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.获取安全数据
        ProfileResult profileResult = (ProfileResult) principalCollection.getPrimaryPrincipal();

        //2.获取权限信息
        Set<String> roles = profileResult.getRoles();
        Set<String> perms = profileResult.getPerms();

        System.out.println("roles:" + JSON.toJSONString(roles));
        System.out.println("perms" + JSON.toJSONString(perms));

        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        String password = new String(token.getPassword());
        //查询用户信息
        SysUser user = sysUserDao.findByNameAndPassword(username, password).get();

        //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        ProfileResult profileResult = new ProfileResult(user);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profileResult, user.getPassword(), getName());
        return info;
    }
}
