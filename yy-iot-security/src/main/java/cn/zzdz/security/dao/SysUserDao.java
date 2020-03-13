package cn.zzdz.security.dao;

import cn.zzdz.security.entity.SysUserEntity;
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

public interface SysUserDao extends JpaRepository<SysUserEntity,String>, JpaSpecificationExecutor<SysUserEntity> {

    Page<SysUserEntity> findAllByCompanyId(String commanyId, Pageable pageable);

    Optional<SysUserEntity> findByName(String name);

    Optional<SysUserEntity> findByNameAndPassword(String name, String password);

    Optional<SysUserEntity> findByNameAndPasswordAndCompanyId(String name, String password, String companyId);
}
