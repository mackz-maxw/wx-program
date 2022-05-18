package wx.miniprogram.todoList.common.utils;

import wx.miniprogram.todoList.common.result.Result;
import wx.miniprogram.todoList.common.result.ResultCode;

public class ControllerSuccessUtil {

    public static Result result(boolean b){
        if (b){
            return Result.success();
        }
        else return Result.fail(ResultCode.ACTION_FAIL);
        //controller add操作问题，返回操作失败3000
    }

}
