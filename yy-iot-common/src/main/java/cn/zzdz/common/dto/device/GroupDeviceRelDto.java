package cn.zzdz.common.dto.device;

import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.common.validated.UpdateMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@ApiModel(description= "设备分组关系")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDeviceRelDto {

    @ApiModelProperty(value = "分组的id")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class})
    @NotNull(groups = {CreatMethod.class, UpdateMethod.class})
    private String groupId;

    @ApiModelProperty(value = "设备的id")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class})
    @NotNull(groups = {CreatMethod.class, UpdateMethod.class})
    private String deviceId;
}
