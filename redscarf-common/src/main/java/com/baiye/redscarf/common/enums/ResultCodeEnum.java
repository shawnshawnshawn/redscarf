package com.baiye.redscarf.common.enums;

/**
 * 返回异常枚举类
 * @author <a href="jiangliuer_shawn@outlook.com">zhangyingdong</a>
 * @Date 2020/5/15 上午11:42
 */
public enum ResultCodeEnum implements EnumMsg<Integer> {

    //--------- 通用异常 -----------
    SUCCESS(0, "请求成功"),

    FAIL(1, "请求失败"),

    SERVER_UNUSERFUL(-1, "服务不可用"),

    SERVER_ERROR(500, "服务器异常"),

    PARAM_NULL(100, "参数不能为空"),

    PARAM_INVALID(101, "参数无效"),

    REQUEST_THIRD_ERROR(102, "请求第三方接口错误"),

    REQUEST_HEADER_LACK(103, "缺少请求头数据"),

    LOGIN_INVALID(104, "登录失效"),

    CATEGORY_NOT_EXIST(105, "分类不存在"),

    CATEGORY_DELETE(106, "分类已被删除"),

    USER_NOT_EXIST(108, "用户不存在"),

    USER_FORBIDDEN(109, "用户已被封禁"),

    WECHAT_AUTH_ERROR(118, "微信授权异常"),

    WECHAT_AUTH_FAIL(119, "微信授权失败"),

    WECHAT_AUTH_GET_BODY_NULL(120, "微信授权获取body为空"),

    LOGIN_NO_OR_PWD_ERROR(124, "账户或密码错误"),

    FILE_SIZE_OVER_THE_LIMIT(134, "文件大小超过限制"),

    UNKNOWN_FILE(135, "未知文件"),

    NO_AUTH_DEL(139, "无权限删除该信息"),

    PARAM_NAME_NOT_NULL(144, "参数字段名称不能为空"),

    PARAM_NULL_WITH(145, "参数{0}不能为空"),

    CHECK_PARAM_ASSIGN_ERROR(146, "参数{0}赋值不能{1}"),

    CHECK_PARAM_ERROR(147, "参数{0}只能在[{1}]之中取值"),

    PARAM_FORMAT_ERROR(148, "CheckParam注解的in格式错误, 请使用'0,1,2'格式"),

    OBJ_TO_JSON_ERROR(149, "转换json失败"),

    JSON_TO_OBJ_ERROR(150, "转换对象失败"),

    JSON_TO_LIST_ERROR(151, "转换数组失败"),

    // ----------- siege ----------------
    SIEGE_TYPE_NOT_EXIST(1001, "围城城门不存在"),

    SIEGE_NOT_EXIST(1002, "围城帖子不存在"),

    ELASTIC_RESULT_NULL(1003, "elastic查询结果为空"),

    SIGNATURE_SERVICE_NOT_EXIST(1004, "不存在的签名服务"),

    PAY_SERVICE_NOT_EXIST(1005, "不存在的支付服务"),





//    --------- 微信异常码 ---------
    ACCESS_TOKEN_INVALID(40001, "微信access_token无效"),

    WECHAT_PRE_PAY_ERROR(40002, "获取微信预支付订单失败"),

    ;

    private Integer code;

    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
