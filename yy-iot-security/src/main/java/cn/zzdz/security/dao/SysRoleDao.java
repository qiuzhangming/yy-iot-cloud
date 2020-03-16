package cn.zzdz.security.dao;

import cn.zzdz.common.entity.security.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 角色
 * 
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */
public interface SysRoleDao extends JpaRepository<SysRole,String>, JpaSpecificationExecutor<SysRole> {
    Page<SysRole> findAllByCompanyId(String companyId, Pageable pageable);
}
