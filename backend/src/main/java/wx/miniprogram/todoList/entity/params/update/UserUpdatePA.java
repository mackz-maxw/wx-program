package wx.miniprogram.todoList.entity.params.update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author yiming
 * @date 2021年05月11日 10:13
 */
@Data
public class UserUpdatePA {

    @NotBlank(message = "昵称不能为空")
    @Size(max = 50, message = "昵称长度不可超过50")
    private String name;

}
