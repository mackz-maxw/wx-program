package wx.miniprogram.todoList.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wx.miniprogram.todoList.common.result.ResultCode;
import wx.miniprogram.todoList.common.utils.WxUtil;
import wx.miniprogram.todoList.entity.dataobject.User;
import wx.miniprogram.todoList.common.result.Result;
import wx.miniprogram.todoList.entity.params.add.UserAddPA;
import wx.miniprogram.todoList.security.validate.ThirdLoginAuthenticationToken;
import wx.miniprogram.todoList.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired//(required = false)类无法装配
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/wxLogin")
    @ResponseBody
    public Result login(String code, HttpServletRequest request) {
        String openId = WxUtil.getOpenId(code);
        User user = userService.getById(openId);
        if(user == null){
            // 发起访客注册
            UserAddPA addVO = new UserAddPA();
            addVO.setId(openId);
            boolean add = userService.add(addVO);
            if(!add){
                return Result.fail(ResultCode.USER_NOT_LOGIN);
            }
            user = userService.getById(openId);
        }
        // 注入框架
        ThirdLoginAuthenticationToken token = new ThirdLoginAuthenticationToken(openId);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String JSessionId = request.getSession().getId();
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",JSessionId);
        map.put("roleId",user.getRole().getValue());
        return Result.success(map);
    }

    @GetMapping("/webLogin")
    public Result webLogin(){
        ThirdLoginAuthenticationToken token = new ThirdLoginAuthenticationToken("cathead001");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Result.success();
    }

}
