package wx.miniprogram.todoList.entity.params.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TodoAddPA {

    @NotBlank(message = "待办标题不能为空")
    @Size(max = 50, message = "标题长度不能超过50")
    private String title;

    @Size(max = 255, message = "详情长度不能超过255")
    private String detail;

    private Date date;

    private Integer isToday;
}
