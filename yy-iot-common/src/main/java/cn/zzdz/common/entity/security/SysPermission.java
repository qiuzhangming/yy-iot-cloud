package cn.zzdz.common.entity.security;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */
@ApiModel(description= "权限信息")
@Data
@Entity
@Table(name = "sys_permission")
public class SysPermission implements Serializable {

	/**
	 * 主键
	 */
	@Id
	@ApiModelProperty(value = "权限id")
	private String id;
	/**
	 * 权限名称
	 */
	@ApiModelProperty(value = "权限名称")
	private String name;
	/**
	 * 权限描述
	 */
	@ApiModelProperty(value = "权限描述")
	private String description;
	/**
	 * 权限类型 1为菜单 2为功能 3为API
	 */
	@ApiModelProperty(value = "权限类型 1为菜单 2为功能 3为API")
	private Integer type;

	/**
	 * 权限code
	 */
	@ApiModelProperty(value = "权限code")
	private String code;
	/**
	 * 企业可见性 0：不可见，1：可见
	 */
	@ApiModelProperty(value = "企业可见性 0：不可见，1：可见")
	private Integer enVisible;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "公司id")
	private Date createTime;

	//@JsonIgnore
	@JsonBackReference
	@ManyToMany(mappedBy="permissions")  //不维护中间表
	private Set<SysRole> roles = new HashSet<>(0);//角色与权限   多对多

}
