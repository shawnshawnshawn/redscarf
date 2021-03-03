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

    CATEGORY_MODE_NOT_EXIST(107, "分类模型不存在"),

    USER_NOT_EXIST(108, "用户不存在"),

    USER_FORBIDDEN(109, "用户已被封禁"),

    DAILY_INFO_NOT_EXIST(110, "生活分享信息不存在"),

    DAILY_INFO_DELETE(111, "生活分享信息已删除"),

    JOB_INFO_NOT_EXIST(112, "招聘求职信息不存在"),

    JOB_INFO_DELETE(113, "招聘求职信息已删除"),

    REALTY_INFO_NOT_EXIST(114, "房屋租赁信息不存在"),

    REALTY_INFO_DELETE(115, "房屋租赁信息已删除"),

    VIDEO_INFO_NOT_EXIST(116, "视频信息不存在"),

    VIDEO_INFO_DELETE(117, "视频信息已删除"),

    WECHAT_AUTH_ERROR(118, "微信授权异常"),

    WECHAT_AUTH_FAIL(119, "微信授权失败"),

    WECHAT_AUTH_GET_BODY_NULL(120, "微信授权获取body为空"),

    REALTY_TYPE_NOT_NULL(121, "房源类型不能为空"),

    ADMIN_NOT_EXIST(122, "管理员不存在"),

    ADMIN_DELETE(123, "管理员已删除"),

    LOGIN_NO_OR_PWD_ERROR(124, "账户或密码错误"),

    ADMIN_EXIST(125, "该账户已存在"),

    CATEGORY_EXIST(126, "分类已存在"),

    NEWS_INFO_NOT_EXIST(127, "咨询信息不存在"),

    NEWS_INFO_DELETE(128, "咨询信息已删除"),

    CATEGORY_ID_AND_CATEGORY_MODE_CANNOT_SAME_TO_NULL(129, "分类id和分类模型不能同时为空"),

    REPLY_NOT_EXIST(130, "回复信息不存在"),

    REPLY_DELETE(131, "回复信息已删除"),

    AD_NOT_EXIST(132, "广告信息不存在"),

    AD_DELETE(133, "广告信息已删除"),

    FILE_SIZE_OVER_THE_LIMIT(134, "文件大小超过限制"),

    UNKNOWN_FILE(135, "未知文件"),

    UP_INFO_NOT_EXIST(136, "点赞信息不存在"),

    COLLECT_TYPE_MUST_NOT_BE_NULL(137, "收藏类型不能为空"),

    COLLECT_TYPE_NOT_EXIST(138, "收藏类型不存在"),

    NO_AUTH_DEL(139, "无权限删除该信息"),

    CATEGORY_CANNOT_DELETE(140, "分类不能删除"),

    CATEGORY_CANNOT_EDIT(141, "分类不能编辑"),

    THIS_CATEGORY_HAVE_INFO_CANNOT_DEL(142, "该分类下存在内容无法删除"),

    SENSITIVE_WORD_FILE_NOT_EXIST(143, "敏感词库不存在"),

    PARAM_NAME_NOT_NULL(144, "参数字段名称不能为空"),

    PARAM_NULL_WITH(145, "参数{0}不能为空"),

    CHECK_PARAM_ASSIGN_ERROR(146, "参数{0}赋值不能{1}"),

    CHECK_PARAM_ERROR(147, "参数{0}只能在[{1}]之中取值"),

    PARAM_FORMAT_ERROR(148, "CheckParam注解的in格式错误, 请使用'0,1,2'格式"),

    OBJ_TO_JSON_ERROR(149, "转换json失败"),

    JSON_TO_OBJ_ERROR(150, "转换对象失败"),

    JSON_TO_LIST_ERROR(151, "转换数组失败"),

    CACHE_KEY_NULL(152, "缓存key不能为空"),

    BANNER_FILE_NOT_EXIST(153, "spring banner不存在"),

    AD_EXIST(154, "营销标题已存在"),

    API_NOT_ALLOW_REQUEST(155, "服务器维护中，暂停请求！"),

    INFO_DELETE(156, "该信息不存在或已被删除"),

    MSG_CHECK_ERROR(157, "微信校验内容异常"),

    INFO_TITLE_ILLEGAL(158, "帖子标题未通过微信内容安全校验，请确定输入标题是否合法！"),

    INFO_ILLEGAL(159, "帖子内容未通过微信内容安全校验，请确定输入内容是否合法！"),

    IMAGE_ILLEGAL(160, "图片未通过微信内容安全校验，请确定上传图片是否合法！"),

    COMMENT_ILLEGAL(161, "评论内容未通过微信内容安全校验，请确定内容是否合法！"),

    TOPIC_INFO_NOT_EXIST(162, "话题不存在"),

    TOPIC_INFO_DELETE(163, "话题已删除"),

    MORE_THAN_MAX_TOPIC_NUM(164, "超过最大话题选择数量"),

    USER_CHARGE_ERROR(165, "用户积分余额充值异常"),

    USER_PAY_ERROR(166, "用户积分支付异常"),

    USER_WALLET_BALANCE_NOT_ENOUGH(167, "用户积分余额不足"),

    SUB_CATEGORY_NOT_EXIST(168, "二级分类不存在"),

    CONFIG_NOT_EXIST(169, "配置不存在"),

    TYPE_NOT_EXIST(170, "不存在的奖励类型"),

    TODAY_ALREADY_SIGN(171, "今日已签到"),

    FORUM_TOPIC_NOT_EXIST(172, "帖子话题关联信息不存在"),

    FORUM_TOPIC_DELETE(173, "帖子话题关联信息已删除"),

    WECHAT_ACCESS_TOKEN_NOT_REFRESH(174, "微信access_token未刷新，请稍后再试！"),

    LANDING_PAGE_NOT_EXIST(175, "落地页不存在"),

    LANDING_PAGE_DEL(176, "落地页已删除"),

    WECHAT_PREPAY_ERROR(177, "微信预支付失败"),

    WECHAT_PRE_PAY_ERROR(178, "获取微信预支付订单失败"),

    WECHAT_PAY_ERROR(179, "微信支付订单失败"),

    ORDER_NOT_EXIST(180, "订单不存在"),

    ORDER_CANCEL(180, "订单已取消"),

    ORDER_COMPLETE(181, "订单已完成"),

    USER_CURRENCY_BALANCE_NULL(182, "用户钱包不存在"),

    USER_WALLET_FORBIDDEN(183, "用户钱包已被封禁，请联系客服"),

    BALANCE_UPDATE_ERROR(184, "用户余额修改失败"),

    REDPACK_AMOUNT_NOT_ENOUGH(185, "红包金额不足"),

    CREATE_REDPACK_ERROR(186, "创建红包异常"),

    USER_BALANCE_NOT_ENOUGH(187, "用户余额不足"),

    CREATE_QR_ERROR(188, "生成qr异常"),

    UPLOAD_FILE_ERROR(189, "转换文件失败"),

    CODE_ERROR_NOT_EXIST(190, "不存在的异常信息"),

    WECHAT_REQUEST_ERROR(191, "请求微信接口异常"),

    REDPACK_NOT_EXIST(192, "红包不存在"),

    REDPACK_ALREADY_RECEIVED(193, "红包已领完"),

    REDPACK_ALREADY_END(194, "红包已结束"),

    USER_REDPACK_ALREADY_RECEIVE(195, "已领取该红包"),

    RECEIVE_REDPACK_ERROR(196, "领取红包异常"),

    WITHDRAW_RECORD_NOT_EXIST(197, "提现记录不存在"),

    WITHDRAW_RECORD_ALREADY_ACCEPT(198, "提现已受理"),

    WITHDRAW_RECORD_COMPLETE(199, "提现已处理完成"),

    UNFREEZE_BALANCE_ERROR(600, "解冻金额异常"),

    CLEAR_FREEZE_BALANCE_ERROR(601, "清除冻结金额异常"),

    FREEZE_BALANCE_ERROR(602, "冻结金额异常"),

    FODDER_CATEGORY_NOT_EXIST(603, "素材分类不存在"),

    FODDER_CATEGORY_DEL(603, "素材分类已删除"),

    VIDEO_FODDER_NOT_EXIST(604, "视频素材不存在"),

    VIDEO_FODDER_DEL(605, "视频素材已删除"),

    REDPACK_AMOUNT_MORE_THAN_LIMIT(606, "红包金额超过最大金额限制"),

    REDPACK_RECEIVE_NUMBER_ILLEGAL(607, "红包领取数量不合法，最大值为100，最小值为1"),

    REDPACK_AMOUNT_LESS_THAN(608, "由于单个用户领取的最小金额为0.01，故该红包最小金额应为{0}}"),

    FODDER_CATEGORY_CANNOT_DEL(609, "该分类不能删除,because有视频-。-"),

    WITHDRAW_NOT_ACCEPT(610, "该提现未被受理"),

    COUNT_TYPE_NOT_EXIST(611, "统计事件不存在"),



//    --------- 微信异常码 ---------
    ACCESS_TOKEN_INVALID(40001, "微信access_token无效"),

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

    public static ResultCodeEnum getByCode(Integer code) {
        ResultCodeEnum res = ResultCodeEnum.WECHAT_REQUEST_ERROR;
        for (ResultCodeEnum value : values()) {
            if (value.code.equals(code)) {
                res =  value;
                break;
            }
        }
        return res;
    }
}
