package cn.zzdz.common.dto.device;

import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.common.validated.SearchMethord;
import cn.zzdz.common.validated.UpdateMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@ApiModel(description= "分组信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfoDto {

    @ApiModelProperty(value = "分组id")
    @Min(value = ID_SIZE, groups = UpdateMethod.class)
    @NotNull(groups = UpdateMethod.class)
    private String id;

    @ApiModelProperty(value = "公司id")
    @Min(value = ID_SIZE, groups = {CreatMethod.class, UpdateMethod.class, SearchMethord.class})
    @NotNull(groups = {CreatMethod.class, UpdateMethod.class, SearchMethord.class})
    private String companyId;

    @ApiModelProperty(value = "分组模式 1:控制柜分组，2:rtu分组，3:网关分组，4:灯杆分组,5:单灯分组")
    @Min(value = 1, groups = {CreatMethod.class, UpdateMethod.class, SearchMethord.class})
    @NotNull(groups = {CreatMethod.class, UpdateMethod.class, SearchMethord.class})
    private Short model;

    @ApiModelProperty(value = "分组类型 1:默认,用于树状图默认的加载 2:其他值:扩展类型,例如用户自定义的分组。")
    @Min(value = 1, groups = {CreatMethod.class, UpdateMethod.class, SearchMethord.class})
    @NotNull(groups = {CreatMethod.class, UpdateMethod.class, SearchMethord.class})
    private Short type;

    @ApiModelProperty(value = "分组名字")
    @Size(min = 2, max = 20, groups = {CreatMethod.class, UpdateMethod.class})
    private String name;

    @ApiModelProperty(value = "备注信息")
    @Size(max = 100, groups = {CreatMethod.class, UpdateMethod.class})
    private String remark;
}