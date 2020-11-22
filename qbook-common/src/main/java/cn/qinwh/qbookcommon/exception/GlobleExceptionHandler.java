package cn.qinwh.qbookcommon.exception;

import cn.qinwh.qbookcommon.utils.ReturnMsg;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ReturnMsg<String> exceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof BindException){
            BindException ex = (BindException) e;
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            //多个错误，取第一个
            FieldError error = fieldErrors.get(0);
            String msg = error.getDefaultMessage();
            return ReturnMsg.fail(msg, null);
        }else {
            return ReturnMsg.fail("系统异常!", null);
        }
    }
}
