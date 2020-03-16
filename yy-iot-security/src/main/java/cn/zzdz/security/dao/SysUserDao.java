package cn.zzdz.security.dao;

import cn.zzdz.common.entity.security.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 
 * 
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */

public interface SysUserDao extends JpaRepository<SysUser,String>, JpaSpecificationExecutor<SysUser> {

    Page<SysUser> findAllByCompanyId(String commanyId, Pageable pageable);

    Optional<SysUser> findByName(String name);

    Optional<SysUser> findByNameAndPassword(String name, String password);

    Optional<SysUser> findByNameAndPasswordAndCompanyId(String name, String password, String companyId);
}
