package wx.miniprogram.todoList.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 响应的个人信息VO
 *
 * @author yiming
 * @date 2021年05月11日 10:10
 */
@Data
@Accessors(chain = true)
public class UserInfoVO {
    // 姓名
    private String name;

    // 学号
    private String studentId;

    // 未完成待办总数
    private Integer waitTodoNumber;

    // 已完成待办总数
    private Integer endTodoNumber;

    // 今日待办总数
    private Integer todayTodoNumber;

}
