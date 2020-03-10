package com.jidu.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 * {
 * success ：是否成功
 * code    ：返回码
 * message ：返回信息
 * //返回数据
 * data：  ：{
 * <p>
 * }
 * }
 */
@Data
@NoArgsConstructor
@ApiModel(value = "返回结果", description = "返回结果")
public class Result<T> {
    @ApiModelProperty(value = "是否成功")
    private boolean success;//是否成功
    @ApiModelProperty(value = "返回码")
    private Integer code;// 返回码
    @ApiModelProperty(value = "返回信息")
    private String message;//返回信息
    @ApiModelProperty(value = "返回数据")
    private T data;// 返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR() {
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }
}
