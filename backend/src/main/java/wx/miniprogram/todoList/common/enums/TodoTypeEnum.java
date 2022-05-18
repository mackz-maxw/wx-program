package wx.miniprogram.todoList.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TodoTypeEnum implements IEnum<Integer> {

    COMMON(0,"普通待办"),
    TODAY(1,"今日待办");

    private int type;

    private String name;

    TodoTypeEnum(int type, String name){
        this.type = type;
        this.name = name;
    }

    @Override
    public Integer getValue(){return this.type; }

    public static TodoTypeEnum getByType(int type){
        return Arrays.stream(values()).filter(e -> e.getType() == type).findFirst().orElse(null);
    }

    public static TodoTypeEnum getByName(String name){
        return Arrays.stream(values()).filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
