package cn.zzdz.device.interceptor;

import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.exception.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 拦截所有 RestController 的异常
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionInterceptor {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception exception, HttpServletResponse response) {
        log.error("拦截到异常：{}", exception.toString());
        exception.printStackTrace();

        // 自定义异常
        if (exception instanceof CustomerException) {
            CustomerException customerException = (CustomerException) exception;
            return new Result(customerException.getResultCode());
        }
        // jsr303校验异常
        else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
            Result result = new Result(ResultCode.PARAMETER_INVALID);
            result.setData(methodArgumentNotValidException.getMessage());

            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            if (bindingResult.hasErrors()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (ObjectError error : bindingResult.getAllErrors()) {
                    stringBuilder.append(error.getDefaultMessage()).append(",");
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length()-1);//删除最后一个字符
                }
                result.setData(stringBuilder.toString());
            }

            return result;
        }
        // sql 异常
        else if (exception instanceof NestedRuntimeException) {
            NestedRuntimeException nestedRuntimeException = (NestedRuntimeException) exception;
            Result result = new Result(ResultCode.SQL_EXECUTE_ERROR);
            result.setData(nestedRuntimeException.getMessage());
            // 获取错误具体描述
            Throwable throwable = nestedRuntimeException.getRootCause();
            if (throwable instanceof SQLException) {
                SQLException sqlException = (SQLException) throwable;
                //responseDto.setCode(sqlException.getErrorCode());
                result.setData(sqlException.getMessage());
            }
            return result;
        }
        // 其他异常
        else {
            return new Result(ResultCode.FAIL, exception.getMessage());
        }
    }
}
