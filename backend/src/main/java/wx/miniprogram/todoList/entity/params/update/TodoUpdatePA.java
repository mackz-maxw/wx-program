package wx.miniprogram.todoList.entity.params.update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TodoUpdatePA {

    //todo_id
    @NotBlank(message = "todo id不能为空")
    private String id;

    @NotBlank(message = "标题不能为空")
    @Size(max = 50, message = "标题长度不能超过50")
    private String title;

    @Size(max = 255, message = "详情长度不能超过255")
    private String detail;

    //todo截止时间
    //仅String可应用NotBlank
    private Date date;

}
