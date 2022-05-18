package wx.miniprogram.todoList.handler.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import wx.miniprogram.todoList.common.result.Result;
import wx.miniprogram.todoList.common.result.ResultCode;

import java.nio.file.AccessDeniedException;


/**
 * @author Godc
 * @description
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //这个注解表名此方法应该处理什么异常
    @ExceptionHandler(Exception.class)
    @ResponseBody//统一返回类型
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

//     由于全局异常的存在只能手动捕获这个异常
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAccessRE(AccessDeniedException ex){
        System.out.println(ex.getMessage());
        return Result.fail(ResultCode.NO_PERMISSION);
    }

    //自定义类异常的处理
    @ExceptionHandler(ZzuException.class)
    @ResponseBody
    public Result error(ZzuException e) {
        e.printStackTrace();
        //返回错误码，统一返回类型方便调用
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String s =  e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(ResultCode.PARAM_NOT_VALID).message(s);
    }


}
