package cn.zzdz.common.entity.security;


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


    public ProfileResult(SysUser userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.companyId = userEntity.getCompanyId();

        for (SysRole sysRole : userEntity.getRoles()) {
            roles.add(sysRole.getCode());
            for (SysPermission permission : sysRole.getPermissions()) {
                perms.add(permission.getCode());
            }
        }
    }
}
