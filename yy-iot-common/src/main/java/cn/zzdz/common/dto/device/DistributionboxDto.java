package cn.zzdz.common.dto.device;

import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.common.validated.UpdateMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@ApiModel(description= "控制柜信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionboxDto {
    @ApiModelProperty(value = "控制柜的id")
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

    @ApiModelProperty(value = "控制柜编号")
    @Size(min = 1, max = 50, groups = {CreatMethod.class, UpdateMethod.class})
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

    @ApiModelProperty(value = "地址信息")
    @Length(max = 50, groups = {CreatMethod.class, UpdateMethod.class})
    private String address;

    @ApiModelProperty(value = "备注信息")
    @Length(max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    private String remark;
}
