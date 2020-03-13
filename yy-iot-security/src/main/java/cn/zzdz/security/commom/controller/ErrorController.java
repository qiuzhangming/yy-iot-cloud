package cn.zzdz.security.commom.controller;

import cn.zzdz.security.commom.entity.Result;
import cn.zzdz.security.commom.entity.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共错误跳转
 */
@RestController
public class ErrorController {

    @RequestMapping("/autherror")
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
