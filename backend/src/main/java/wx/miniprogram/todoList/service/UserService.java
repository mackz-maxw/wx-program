package wx.miniprogram.todoList.service;

import wx.miniprogram.todoList.entity.dataobject.User;
import com.baomidou.mybatisplus.extension.service.IService;
import wx.miniprogram.todoList.entity.params.add.UserAddPA;

/**
 * 服务类
 *
 * @author cbj
 * @author yiming
 * @since 2021-04-26
 */
public interface UserService extends IService<User> {

    public boolean add(UserAddPA addVO);
}
