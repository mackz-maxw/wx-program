package wx.miniprogram.todoList.common.result;

/**
 * @Author: Hutengfei
 * @Description: 返回码定义
 * 规定:
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * @Date Create in 2019/7/22 19:28
 */
public enum ResultCode {
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败，说明出了大问题，去404 */
    COMMON_FAIL(201, "失败"),

    /*操作失败*/
    ACTION_FAIL(3000,"操作失败，请稍后重试"),// 自定义操作失败，将msg以弹窗的形式呈现
    NO_PERMISSION(3001, "没有权限"),// 将msg以弹窗的形式呈现
    PARAM_NOT_VALID(3002, "参数无效,请重新输入"),// 将msg以弹窗的形式呈现

    /*渲染失败*/
    GET_FAIL(4001,"加载失败，请稍后重试"),

    /* 登录相关 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    /* 注册相关 */
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在");


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}