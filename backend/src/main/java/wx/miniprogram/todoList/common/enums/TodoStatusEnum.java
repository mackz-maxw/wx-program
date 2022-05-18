package wx.miniprogram.todoList.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

import java.util.Arrays;

/*待办状态枚举*/

@Getter
public enum TodoStatusEnum implements IEnum<Integer> {

    UNDO(0,"未完成待办"),
    DONE(1,"已完成待办"),
    OVERDO(2,"超时待办");

    private int type;

    private String name;

    TodoStatusEnum(int type, String name){
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getValue(){return this.type;}

    public static TodoStatusEnum getByType(int type){
        return Arrays.stream(values()).filter(e -> e.getType() == type).findFirst().orElse(null);
    }

    public static TodoStatusEnum getByName(String name){
        return Arrays.stream(values()).filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
