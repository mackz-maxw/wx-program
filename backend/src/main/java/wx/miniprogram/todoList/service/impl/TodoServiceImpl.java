package wx.miniprogram.todoList.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wx.miniprogram.todoList.common.enums.TodoStatusEnum;
import wx.miniprogram.todoList.common.enums.TodoTypeEnum;
import wx.miniprogram.todoList.common.utils.TodoListUtil;
import wx.miniprogram.todoList.entity.dataobject.Todo;
import wx.miniprogram.todoList.entity.params.add.TodoAddPA;
import wx.miniprogram.todoList.entity.params.update.TodoUpdatePA;
import wx.miniprogram.todoList.entity.vo.TodoVO;
import wx.miniprogram.todoList.mapper.TodoMapper;
import wx.miniprogram.todoList.service.TodoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements TodoService {

    @Override
    public boolean add(TodoAddPA todoAddPA){
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoAddPA, todo);
        todo.setUserId(TodoListUtil.getUserId())
            .setIsToday(TodoTypeEnum.COMMON)
            .setIsDelete(0)
            .setStatus(TodoStatusEnum.UNDO);
        return save(todo);
    }

    @Override
    public boolean remove(String id){
        boolean remove = removeById(id);
        return remove;
    }

    @Override
    public boolean update(TodoUpdatePA todoUpdatePA) {
        Todo todo = getById(todoUpdatePA.getId());
        todo.setTitle(todoUpdatePA.getTitle())
            .setDetail(todoUpdatePA.getDetail())
            .setDate(todoUpdatePA.getDate());
        return updateById(todo);
    }

    @Override
    public boolean addTodayTodo(TodoAddPA todoAddPA){
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoAddPA, todo);
        todo.setUserId(TodoListUtil.getUserId())
                .setIsToday(TodoTypeEnum.TODAY)
                .setIsDelete(0)
                .setStatus(TodoStatusEnum.UNDO);
        return save(todo);
    }

    @Override
    public boolean moveToToday(String id){
        Todo todo = getById(id);
        todo.setIsToday(TodoTypeEnum.TODAY);
        return updateById(todo);
    }

    @Override
    public boolean removeFromToday(String id){
        Todo todo = getById(id);
        todo.setIsToday(TodoTypeEnum.COMMON);
        return updateById(todo);
    }

    @Override
    @Transactional(rollbackFor = {
            NullPointerException.class,
            RuntimeException.class,
            Exception.class
    })
    //???????????????????????????select???where??????????????????????????????
    public boolean finish(String id){
        Todo todo = getById(id);
        todo.setStatus(TodoStatusEnum.DONE);
        boolean update = updateById(todo);
        return update;
    }

    //?????????????????????????????????
    @Override
    public List<TodoVO> pageTodoFinished(long current, long limit){
        Page<Todo> todoPage = new Page<>(current, limit);
        QueryWrapper<Todo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", TodoStatusEnum.DONE)
                .eq("user_id", TodoListUtil.getUserId())
                .orderByDesc("modified_date");
        this.page(todoPage, wrapper);//?????????????????????????????????????????????????????????todoPage?????????
        List<Todo> records = todoPage.getRecords();//???????????????
        //???????????????????????????
        List<TodoVO> todoVOs = new ArrayList<>();
        TodoVO todoVO;
        for(Todo record : records){
            todoVO = new TodoVO();
            BeanUtils.copyProperties(record, todoVO);
            todoVOs.add(todoVO);
        }
        return todoVOs;
    }

    //???????????????????????????
    @Override
    public List<TodoVO> ListTodo(){
        QueryWrapper<Todo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", TodoListUtil.getUserId())
                .eq("status", TodoStatusEnum.UNDO);
        List<Todo> todos = baseMapper.selectList(wrapper);
        List<TodoVO> todoVOs = new ArrayList<>();
        TodoVO todoVO;
        for(Todo todo : todos){
            todoVO = new TodoVO();
            BeanUtils.copyProperties(todo, todoVO);
            todoVOs.add(todoVO);
        }
        return todoVOs;
    }



    //????????????????????????
    @Override
    public boolean refreshUnfinished() {
        LocalDate today = LocalDate.now();
        //LocalDate today = LocalDate.of(2022,5,7);

        /*if(today.getDayOfYear()<LocalDate.now().getDayOfYear()
                || today.getYear()<LocalDate.now().getYear()){

        } else {
            return false;
        }*/
        QueryWrapper<Todo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", TodoListUtil.getUserId())
                .eq("status",TodoStatusEnum.UNDO)
                .lt("DAYOFYEAR(date)",today.getDayOfYear())
                .or()
                .lt("YEAR(date)",today.getYear());
        Todo unfinished = new Todo().setIsToday(TodoTypeEnum.COMMON).setStatus(TodoStatusEnum.OVERDO);
        return update(unfinished,wrapper);
    }

    /**
     * ??????????????????????????????
     */
    @Override
    public Map<String, Integer> countTodo() {
        Map<String, Integer> map = new HashMap<>();
        QueryWrapper<Todo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", TodoListUtil.getUserId());
        List<Todo> todoList = baseMapper.selectList(wrapper);

        // ?????????????????????
        int undone = 0;
        // ?????????????????????
        int done = 0;
        // ??????????????????
        int overdo = 0;
        // ??????????????????
        int today = 0;

        TodoStatusEnum status; // ????????????
        TodoTypeEnum isToday;  // ????????????
        for (Todo todo : todoList) {
            status = todo.getStatus();
            if (status == TodoStatusEnum.UNDO) {
                undone += 1;
            } else if (status == TodoStatusEnum.DONE) {
                done += 1;
            } else if (status == TodoStatusEnum.OVERDO) {
                overdo += 1;
            }
            isToday = todo.getIsToday();
            if (isToday == TodoTypeEnum.TODAY) {
                today += 1;
            }
        }
        map.put("undone", undone);
        map.put("done", done);
        map.put("overdo", overdo);
        map.put("today", today);
        return map;
    }
}
