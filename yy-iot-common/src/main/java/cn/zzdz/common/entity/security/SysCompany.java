package cn.zzdz.common.entity.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description= "公司信息")

@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_company")
public class SysCompany implements Serializable {

    @Id
    @ApiModelProperty(value = "公司id")
    private String id;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    /**
     * @OneToMany(mappedBy="company",cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
     *     //拥有mappedBy注解的实体类为关系被维护端，双向关联需要用此注解
     *     //mappedBy="company"中的company是Employee中的company属性
     *     //orphanRemoval为true,表示会先直接删除对应的子表数据，级联更新此配置最为关键
     */
    //@OneToMany
    //@JoinColumn(name = "company_id")
    //private Set<SysUserEntity> users;
}
