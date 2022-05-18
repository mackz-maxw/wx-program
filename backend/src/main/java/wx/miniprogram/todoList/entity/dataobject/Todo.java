package wx.miniprogram.todoList.entity.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import wx.miniprogram.todoList.common.enums.TodoStatusEnum;
import wx.miniprogram.todoList.common.enums.TodoTypeEnum;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@ApiModel(value = "Todo数据对象", description = "待办数据")
public class Todo implements Serializable {

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;

    private String userId;

    @ApiModelProperty(value = "待办标题")
    private String title;

    @ApiModelProperty(value = "待办详情")
    private String detail;

    @ApiModelProperty(value = "截至时间")
    private Date date;

    @ApiModelProperty(value = "待办状态")
    private TodoStatusEnum status;

    @ApiModelProperty(value = "是否是今日待办")
    private TodoTypeEnum isToday;

    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedDate;

    @ApiModelProperty(value = "逻辑删除：1（true）已删除，0（false）未删除")
    @TableLogic
    //@TableLogic 注解将会在 select 语句的 where 条件添加条件，过滤掉已删除数据，且使用 wrapper.entity 生成的 where 条件会忽略该字段
    private Integer isDelete;

}
