package cn.zzdz.common.entity.security;

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
 * 
 * 
 * @author joe
 * @email qiuzhangming@gmail.com
 * @date 2020-03-02 17:13:03
 */

@ApiModel(description= "用户信息")
@Getter
@Setter
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

	/**
	 * 用户id
	 */
	@Id
	@ApiModelProperty(value = "用户id")
	private String id;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String name;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String mobile;
	/**
	 * 状态  0：禁用   1：正常
	 */
	@ApiModelProperty(value = "状态：0：禁用 1：正常")
	private Integer status;
	/**
	 * 企业ID
	 */
	@ApiModelProperty(value = "企业id，不能为空")
	private String companyId;
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门id，可以为空。")
	private String departmentId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 *  用户-角色关系表
	 *  JsonIgnore: 忽略json转化
	 */
	//@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="sys_user_role",
			joinColumns={@JoinColumn(name="user_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")}
	)
	private Set<SysRole> roles = new HashSet<>();//用户与角色   多对多


	/**
	 *  @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	 *  可选属性optional=false,表示company不能为空
	 *  @JoinColumn(name="company_id")
	 *  设置关联字段(外键)，另注意因为这里已经写了 company_id,所以不用在写companyId这个属性
	 */
//	@JsonBackReference
//	@ManyToOne
//	@JoinColumn(name = "company_id")
//	private SysCompanyEntity company;
}
