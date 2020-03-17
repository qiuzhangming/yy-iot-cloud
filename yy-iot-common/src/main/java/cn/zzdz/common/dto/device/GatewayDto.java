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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@ApiModel(description= "网关信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatewayDto {

    @ApiModelProperty(value = "网关的id")
    @Min(value = ID_SIZE, groups = UpdateMethod.class)
    @NotNull(groups = UpdateMethod.class)
    private String id;

    @ApiModelProperty(value = "公司id")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class})
    @NotNull(groups = {CreatMethod.class})
    private String companyId;

    @ApiModelProperty(value = "分组id")
    @Min(value = ID_SIZE, groups = {CreatMethod.class})
    @NotNull(groups = {CreatMethod.class})
    private String groupId;

    @ApiModelProperty(value = "上级设备id,没有上级设备可不填")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class})
    private String pid;

    @ApiModelProperty(value = "名称")
    @Size(min = 2, max = 20, groups = {CreatMethod.class, UpdateMethod.class})
    private String name;

    @ApiModelProperty(value = "通讯方式,0:无需通讯，通常为虚拟设备或灯杆 1:GPRS 2:NB")
    @Range(min = 0, max = 2, groups = {CreatMethod.class, UpdateMethod.class})
    private Short commType;


    @ApiModelProperty(value = "硬件版本 1:老版本的 2:机场版本")
    @Range(min = 10, max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    @NotNull(groups = {CreatMethod.class})
    private Short hardwareVersion;

    @ApiModelProperty(value = "软件版本 默认填1")
    @Range(min = 10, max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    private Short softwareVersion;

    @ApiModelProperty(value = "通讯卡的imei")
    @Length(min = 1, max = 20, groups = {CreatMethod.class, UpdateMethod.class})
    private String imei;

    @ApiModelProperty(value = "dtu的id,机场版本无需填写")
    @Length(min = 8, max = 8, groups = {CreatMethod.class, UpdateMethod.class}, message = "dtuId长度固定为8位")
    private String dtuId;

    @ApiModelProperty(value = "rtu的主板编号,机场版本无需填写")
    @Range(min = 1, max = 65535, groups = {CreatMethod.class, UpdateMethod.class})
    private String mcuId;

    @ApiModelProperty(value = "设备编号，只有机场版本需要填写")
    @Length(min = 1, max = 16, groups = {CreatMethod.class, UpdateMethod.class})
    private String deviceSn;

    @ApiModelProperty(value = "经度")
    @Pattern(regexp = "[\\-+]?(0?\\d{1,2}|0?\\d{1,2}\\.\\d{1,15}|1[0-7]?\\d|1[0-7]?\\d\\.\\d{1,15}|180|180\\.0{1,15})",
            message = "经度格式错误",
            groups = {CreatMethod.class, UpdateMethod.class}
    )
    private String lng;

    @ApiModelProperty(value = "纬度")
    @Pattern(regexp = "[\\-+]?([0-8]?\\d|[0-8]?\\d\\.\\d{1,15}|90|90\\.0{1,15})",
            message = "纬度格式错误",
            groups = {CreatMethod.class, UpdateMethod.class}
    )
    private String lat;

    @ApiModelProperty(value = "备注信息")
    @Length(max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    private String remark;
}
