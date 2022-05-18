package wx.miniprogram.todoList.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

import java.util.Arrays;

/**
 * 角色枚举
 */
@Getter
public enum RoleEnum implements IEnum<Integer> {

    VISITOR(0, "ROLE_VISITOR", "访客"),
    USER(1, "ROLE_USER", "普通用户"),
    COURIER(2, "ROLE_COURIER", "接单员"),
    ADMIN(3, "ROLE_ADMIN", "系统管理员");

    private int type;

    private String name;

    private String cnName;

    RoleEnum(int type, String name, String cnName) {
        this.type = type;
        this.name = name;
        this.cnName = cnName;
    }

    @Override
    public Integer getValue() {
        return this.type;
    }

    public static RoleEnum getByType(int type) {
        return Arrays.stream(values()).filter(e -> e.getType() == type).findFirst().orElse(null);
    }

    public static RoleEnum getByName(String name) {
        return Arrays.stream(values()).filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
