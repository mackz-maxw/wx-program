package wx.miniprogram.todoList.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wx.miniprogram.todoList.common.result.Result;
import wx.miniprogram.todoList.common.result.ResultCode;
import wx.miniprogram.todoList.common.utils.ControllerSuccessUtil;
import wx.miniprogram.todoList.common.utils.ParamCheckUtil;
import wx.miniprogram.todoList.entity.params.add.TodoAddPA;
import wx.miniprogram.todoList.entity.params.update.TodoUpdatePA;
import wx.miniprogram.todoList.entity.vo.TodoVO;
import wx.miniprogram.todoList.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Api(description = "个人待办相关")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @ApiOperation(value = "新建个人待办")
    @PostMapping("/add")
    public Result addMine(@ApiParam(name = "todoMeAddPA", value = "待增加待办", required = true)
                              @Valid @RequestBody TodoAddPA todoAddPA){

        boolean add = todoService.add(todoAddPA);
/*        if (add){
            return "添加成功";
        } else{
            return "添加失败";
        }*/
        return ControllerSuccessUtil.result(add);
    }

    @ApiOperation(value = "逻辑删除待办")
    @DeleteMapping("/delete/{id}")
    public Result deleteMine(@ApiParam(name = "id", value = "待删除待办id", required = true)
                                         @PathVariable("id") String id){
        if(ParamCheckUtil.isNotRightId(id)){
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean remove = todoService.remove(id);
        return ControllerSuccessUtil.result(remove);
    }

    @ApiOperation(value = "更新待办")
    @PostMapping("/update")
    public Result updateMine(@ApiParam(name = "todoUpdatePA", value = "更新待办", required = true)
                                 @Valid @RequestBody TodoUpdatePA todoUpdatePA){
        if(ParamCheckUtil.isNotRightId(todoUpdatePA.getId())){
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean update = todoService.update(todoUpdatePA);
        return ControllerSuccessUtil.result(update);
    }

    //虽然请求体内的isToday是false，但是传回数据后在service层中有覆盖
    @ApiOperation(value = "添加今日待办")
    @PostMapping("/add-today")
    public Result addTodayTodo(@ApiParam(name = "todoAddPA", value = "添加今日待办", required = true)
                                           @Valid @RequestBody TodoAddPA todoAddPA){
        boolean addToday = todoService.addTodayTodo(todoAddPA);
        return ControllerSuccessUtil.result(addToday);
    }

    //isToday是true的时候，date怎么办呢？
    @ApiOperation(value = "移入今日待办")
    @PutMapping("/move-today/{id}")
    public Result moveToToday(@ApiParam(name = "id", value = "移入今日待办id", required = true)
                                          @PathVariable("id") String id){
        if(ParamCheckUtil.isNotRightId(id)){
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean move = todoService.moveToToday(id);
        return ControllerSuccessUtil.result(move);
    }

    @ApiOperation(value = "移出今日待办")
    @PutMapping("/remove-today/{id}")
    public Result removeFromToday(@ApiParam(name = "id", value = "移出今日待办id", required = true)
                                              @PathVariable("id") String id){
        if(ParamCheckUtil.isNotRightId(id)){
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean remove = todoService.removeFromToday(id);
        return ControllerSuccessUtil.result(remove);
    }

    @ApiOperation(value = "完成待办")
    @PutMapping("/finish/{id}")
    public Result Finish(@ApiParam(name = "id", value = "完成待办id", required = true)
                                     @PathVariable("id") String id){
        if(ParamCheckUtil.isNotRightId(id)){
            return Result.fail(ResultCode.PARAM_NOT_VALID).message("id格式错误");
        }
        boolean finish = todoService.finish(id);
        return ControllerSuccessUtil.result(finish);
    }

    @ApiOperation(value = "分页查询已完成待办")
    @GetMapping("/page-finished/{current}/{limit}")
    public Result pageTodoFinished(@ApiParam(name = "current", value = "当前页码", required = true)
                                               @PathVariable("current") long current,
                                   @ApiParam(name = "limit", value = "每页记录数", required = true)
                                           @PathVariable("limit") long limit){
        todoService.refreshUnfinished();
        List<TodoVO> todoVOS = todoService.pageTodoFinished(current, limit);
        return Result.success().data("row", todoVOS);
    }

    @ApiOperation(value = "查询未完成待办")
    @GetMapping("/list-todo")
    public Result ListTodo(){
        todoService.refreshUnfinished();
        List<TodoVO> todoVOS = todoService.ListTodo();
        return Result.success().data("row", todoVOS);
    }
}
