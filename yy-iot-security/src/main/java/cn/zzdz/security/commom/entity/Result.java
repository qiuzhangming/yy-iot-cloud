package cn.zzdz.security.commom.entity;

import cn.zzdz.security.commom.entity.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description= "返回响应数据")
@Data
@NoArgsConstructor
public class Result {
    @ApiModelProperty(value = "是否成功")
    private boolean success;//是否成功

    @ApiModelProperty(value = "错误编号")
    private Integer code;// 返回码

    @ApiModelProperty(value = "错误信息")
    private String message;//返回信息

    @ApiModelProperty(value = "返回对象")
    private Object data;// 返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code,Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code,String message,boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR(){
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }
}
