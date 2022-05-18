package wx.miniprogram.todoList.handler.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Godc
 * @description
 */
@Data
@AllArgsConstructor//生成有参构造器
@NoArgsConstructor//生成无参构造器
public class ZzuException extends RuntimeException{
    //定义异常属性
    private String msg;
    //状态码
    private Integer code;
}
