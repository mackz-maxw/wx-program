package wx.miniprogram.todoList.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wx.miniprogram.todoList.common.enums.RoleEnum;

/**
 * <p>
 * 
 * </p>
 *
 * @author cbj
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="user对象", description="")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "角色")
    @TableField("role_id")
    @JsonValue
    private RoleEnum role;

    private String name;

    private String studentId;

    private String password;

    private String verifyCode;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @TableLogic
    private Integer isDelete;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>() {{
            add(new SimpleGrantedAuthority(getRole().getName()));
        }};
    }

    @Override
    public String getUsername() {
        return null;
    }


    /**
     * 是否启用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

}
