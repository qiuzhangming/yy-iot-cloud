package cn.zzdz.common.exception;

import cn.zzdz.common.entity.result.ResultCode;
import lombok.Data;

@Data
public class CustomerException extends RuntimeException {

    private ResultCode resultCode;

    public CustomerException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
