package cn.zzdz.common.entity.security;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * 
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */
@ApiModel(description= "角色信息")
@Getter
@Setter
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {

	/**
	 * 
	 */
	@Id
	@ApiModelProperty(value = "角色id")
	private String id;
	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称")
	private String name;
	/**
	 * 企业id
	 */
	@ApiModelProperty(value = "企业id")
	private String companyId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 说明
	 */
	@ApiModelProperty(value = "角色说明")
	private String description;

	/**
	 * 角色code
	 */
	@ApiModelProperty(value = "角色code")
	private String code;

	/**
	 * 角色与用户
	 * cascade = CascadeType.REFRESH, fetch = FetchType.EAGER
	 */
	//@JsonIgnore
	@JsonBackReference
	@ManyToMany(mappedBy="roles")  //不维护中间表
	private Set<SysUser> users = new HashSet<>(0);//角色与用户   多对多

	/**
	 * 角色与权限
	 */
	//@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="sys_role_permission",
			joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="permission_id",referencedColumnName="id")})
	private Set<SysPermission> permissions = new HashSet<>();//角色与模块  多对多

}
