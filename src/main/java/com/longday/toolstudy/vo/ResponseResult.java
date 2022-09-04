package com.longday.toolstudy.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.longday.toolstudy.enums.AppHttpCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author 君
 */
@ApiModel(value = "ResponseResult",description = "响应数据类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("响应提示信息")
    private String msg;
    @ApiModelProperty("响应数据")
    private T data;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @ApiOperation("返回请求错误代码和信息")
    public static <T> ResponseResult<T> errorResult(int code, String msg) {
        ResponseResult<T>  result = new ResponseResult<>();
        return result.error(code, msg);
    }
    @ApiOperation("返回请求正确代码和信息")
    public static <T> ResponseResult<T> okResult() {
        return new ResponseResult<>();
    }
    @ApiOperation("返回请求正确代码和信息")
    public static <T> ResponseResult<T> okResult(int code, String msg) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.ok(code, null, msg);
    }
    @ApiOperation("返回请求正确代码、信息和请求到的数据")
    public static <T> ResponseResult<T> okResult(T data) {
        ResponseResult<T> result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }
    @ApiOperation("返回请求错误代码和信息")
    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }
    @ApiOperation("返回请求错误代码和信息")
    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getMsg());
    }

    private static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg){
        return okResult(enums.getCode(),msg);
    }
    @ApiOperation("默认请求错误信息")
    public ResponseResult<T> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }
    @ApiOperation("默认请求正确信息")
    public ResponseResult<T> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }
    @ApiOperation("默认请求正确信息和数据")
    public ResponseResult<T> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }
    @ApiOperation("返回数据")
    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}