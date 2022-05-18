package wx.miniprogram.todoList.security.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wx.miniprogram.todoList.entity.dataobject.User;
import wx.miniprogram.todoList.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 三方登陆 UserDetailService，通过用户ID读取信息
 *
 * @author jitwxs
 * @since 2019/1/8 23:37
 */
@Service
public class ThirdLoginUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.getById(openId);
        // 判断用户是否存在
        if (user == null) {
            System.out.println("用户不存在");
            return null;
        }
        // 添加权限
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return user;
    }
}
