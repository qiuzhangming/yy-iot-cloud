package cn.zzdz.security.commom.entity;


import cn.zzdz.security.entity.SysPermissionEntity;
import cn.zzdz.security.entity.SysRoleEntity;
import cn.zzdz.security.entity.SysUserEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * 精简用户认证信息
 */
@Setter
@Getter
public class ProfileResult implements Serializable {
    private String id;
    private String name;
    private String companyId;
    private Set<String> roles = new HashSet<>();
    private Set<String> perms = new HashSet<>();


    public ProfileResult(SysUserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.companyId = userEntity.getCompanyId();

        for (SysRoleEntity sysRoleEntity : userEntity.getRoles()) {
            roles.add(sysRoleEntity.getCode());
            for (SysPermissionEntity permission : sysRoleEntity.getPermissions()) {
                perms.add(permission.getCode());
            }
        }
    }
}
