package wx.miniprogram.todoList.controller.api;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wx.miniprogram.todoList.service.UserService;

/**
 * 前端控制器
 *
 * @author cbj
 * @author yiming
 * @since 2021-04-26
 */
@Api(description = "用户相关")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

}

