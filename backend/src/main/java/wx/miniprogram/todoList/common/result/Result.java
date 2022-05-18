package wx.miniprogram.todoList.common.result;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Hutengfei
 * @Description: 统一返回实体
 * @Date Create in 2019/7/22 19:20
 */
@Data
public class Result implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();

/*    public Result() {

    }*/

    public Result(boolean success) {
        this.success = success;
        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.message = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }


    //注意和ResultTool的success对应
    public Result(String key, Object value) {
        this.success = true;
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data.put(key, value);
    }

    //注意和ResultTool的success对应
    public Result(Map<String,Object> map) {
        this.success = true;
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = map;
    }

    //注意和ResultTool的fail对应
    public Result(ResultCode resultEnum) {
        this.success = false;
        this.code = resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode();
        this.message = resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage();
    }

    //注意和ResultTool的fail对应
    public Result(String message) {
        this.success = false;
        this.code = ResultCode.COMMON_FAIL.getCode();
        this.message = message == null ? ResultCode.COMMON_FAIL.getMessage() : message;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public static Result success() {
        return new Result(true);
    }

    public static Result success(String key, Object value) {
        return new Result(key,value);
    }

    public static Result success(Map<String,Object> map) {
        return new Result(map);
    }

    public static Result fail() {
        return new Result(false);
    }

    public static Result fail(String message) {
        return new Result(message);
    }

    public static Result fail(ResultCode resultEnum) {
        return new Result(resultEnum);
    }




//
//
//    public JsonResult(boolean success, Map<String,Object> data) {
//        this.success = success;
//        this.code = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
//        this.message = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
//        this.data = data;
//    }
//
//    public JsonResult(boolean success, ResultCode resultEnum, Map<String,Object> data) {
//        this.success = success;
//        this.code = success ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
//        this.message = success ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
//        this.data = data;
//    }
}