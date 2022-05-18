package wx.miniprogram.todoList.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wx.miniprogram.todoList.entity.dataobject.Todo;
import wx.miniprogram.todoList.entity.params.add.TodoAddPA;
import wx.miniprogram.todoList.entity.params.update.TodoUpdatePA;
import wx.miniprogram.todoList.entity.vo.TodoVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface TodoService extends IService<Todo> {
    //新建待办
    boolean add(TodoAddPA todoAddPA);

    //删除待办(mybatis plus自带)
    //@Override
    boolean remove(String id);

    //更新待办
    boolean update(TodoUpdatePA todoUpdatePA);

    //新建今日待办
    boolean addTodayTodo(TodoAddPA todoAddPA);

    //移入今日待办
    boolean moveToToday(String id);

    //移出今日待办
    boolean removeFromToday(String id);

    //完成待办（状态更新）
    boolean finish(String id);

    //分页查询所有已完成待办
    List<TodoVO> pageTodoFinished(long current, long limit);

    //查询所有未完成待办
    List<TodoVO> ListTodo();

    //刷新待办完成状态（检测是否过期）
    boolean refreshUnfinished();

    //封装各待办总数
    Map<String, Integer> countTodo();
}
