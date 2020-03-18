package cn.zzdz.common.dto.device;

import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.common.validated.UpdateMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@ApiModel(description= "单灯控制器信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LightcontrollerDto {
    @ApiModelProperty(value = "单灯控制器的id")
    @Min(value = ID_SIZE, groups = UpdateMethod.class)
    //@NotNull(groups = UpdateMethod.class)
    private String id;

    @ApiModelProperty(value = "公司id")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class})
    @NotNull(groups = {CreatMethod.class})
    private String companyId;

    @ApiModelProperty(value = "上级设备id,没有上级设备可不填")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class})
    private String pid;

    @ApiModelProperty(value = "名称")
    @Size(min = 2, max = 20, groups = {CreatMethod.class, UpdateMethod.class})
    private String name;

    @ApiModelProperty(value = "通讯方式,0:无需通讯，通常为虚拟设备或灯杆 1:GPRS 2:NB")
    @Range(min = 0, max = 2, groups = {CreatMethod.class, UpdateMethod.class})
    private Short commType;

    @ApiModelProperty(value = "硬件版本 1:老版本 2:NB单灯 3:机场单灯")
    @Range(min = 1, max = 3, groups = {CreatMethod.class, UpdateMethod.class})
    @NotNull(groups = {CreatMethod.class})
    private Short hardwareVersion;

    @ApiModelProperty(value = "软件版本,默认填1")
    @Range(min = 1, max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    private Short softwareVersion;

    @ApiModelProperty(value = "使用类型 1:主灯 2:副灯 3:灯带 4:机场高杆灯")
    @Range(min = 1, max = 10, groups = {CreatMethod.class, UpdateMethod.class})
    private Short usageType;

    @ApiModelProperty(value = "设备是否启用")
    private Boolean enabledFlag;

    @ApiModelProperty(value = "通讯卡的imei,若为nb类型必填")
    @Length(min = 1, max = 20, groups = {CreatMethod.class, UpdateMethod.class})
    private String imei;

    @ApiModelProperty(value = "设备编号,老版本的填主板编号,nb不填")
    @Length(min = 1, max = 16, groups = {CreatMethod.class, UpdateMethod.class})
    private String deviceSn;

    @Size(max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    private String remark;
}
