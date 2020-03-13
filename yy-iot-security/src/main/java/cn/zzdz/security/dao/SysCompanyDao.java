package cn.zzdz.security.dao;

import cn.zzdz.security.entity.SysCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 
 * 
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */

public interface SysCompanyDao extends JpaRepository<SysCompanyEntity,String>, JpaSpecificationExecutor<SysCompanyEntity> {

}
