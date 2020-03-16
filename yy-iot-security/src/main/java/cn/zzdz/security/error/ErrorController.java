package cn.zzdz.security.error;

import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共错误跳转
 */
@RestController
public class ErrorController {

    @GetMapping("/autherror")
    public Result autherror(int code) {
        switch (code) {
            case 1:
                return new Result(ResultCode.UNAUTHENTICATED);
            case 2:
                return new Result(ResultCode.UNAUTHORISE);
            default:
                return new Result(ResultCode.FAIL);
        }
        //return code == 1 ? "未登录" : "未授权";
    }

}
