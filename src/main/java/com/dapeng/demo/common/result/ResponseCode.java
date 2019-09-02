package com.dapeng.demo.common.result;

/**
 * API 统一返回状态码
 *
 * @author liuxz
 */
public enum ResponseCode {

    /* 公共状态码 */
    SUCCESS(200, "成功"), // 成功
    FAILED(400, "失败"),//失败
    UNAUTHORIZED(401, "签名错误"), //未认证（签名错误）
    NOT_FOUND(404, "此接口不存在"), //接口不存在
    INTERNAL_SERVER_ERROR(500, "系统繁忙,请稍后再试"), //服务器内部错误

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    LOGIN_CREDENTIAL_EXISTED(20006, "凭证已存在"),

    /* 业务错误：50001-59999 */

    /*审测错误：51000-51999*/
    VRFY_FAILED_TO_EXECUTE_TASK(51000, "执行流程任务失败"),
    VRFY_FAILED_TO_DEPLOY(51001, "部署流程模板失败"),
    VRFY_FAILED_TO_START_INSTANCE(51003, "启动流程实例失败");


    private Integer code;

    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}