package wx.miniprogram.todoList.service.impl;

import wx.miniprogram.todoList.common.enums.RoleEnum;
import wx.miniprogram.todoList.entity.dataobject.User;
import wx.miniprogram.todoList.entity.params.add.UserAddPA;
import wx.miniprogram.todoList.mapper.UserMapper;
import wx.miniprogram.todoList.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author cbj
 * @author yiming
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean add(UserAddPA addVO) {
        User user = new User();
        user.setId(addVO.getId());
        user.setRole(RoleEnum.VISITOR);
        return save(user);
    }

}
