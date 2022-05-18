package wx.miniprogram.todoList.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import wx.miniprogram.todoList.common.enums.RoleEnum;
import wx.miniprogram.todoList.entity.dataobject.User;

public class TodoListUtil {

    public static String getUserId(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public static boolean isCourier(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getRole().equals(RoleEnum.COURIER);
    }

}
