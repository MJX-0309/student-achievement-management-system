package edu.zhku.boot.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author MJX
 * @date 2021/09/18
 */
@Data
@ApiModel("全局返回结果类")
public class Result<T> {
    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("相应信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    private Result(){};

    private Result(Integer code, String message){
        setCode(code);
        setMessage(message);
    }
    private Result(Integer code, String message, T data){
        setCode(code);
        setMessage(message);
        if (data!=null){
            setData(data);
        }
    }
    public static Result<String> success(){
        return success("操作成功");
    }

    public static <T> Result<T> success(T data){
        return success("操作成功",data);
    }
    public static <T> Result<T> success(String message,T data){
        return new Result<>(HttpStatus.OK.value(), message,data);
    }


    public static <T> Result<T> error() {
        return Result.error("操作失败");
    }


    public static <T> Result<T> error(String msg) {
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> Result<T> error(int code,String msg) {
        return new Result<T>(code, msg);
    }
}
