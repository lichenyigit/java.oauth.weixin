package oauth.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

/**
 * Created by renhui on 2017/5/8.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    protected int errorCode;
    protected String errorDescription;
    private String requestId;
    private T result;
    private Long totalCount;


    public Result(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public static Result build() {
        Result result = new Result();
        result.errorCode = 0;
        result.errorDescription = "success";
        result.requestId = UUID.randomUUID().toString();
        return result;
    }

    public Result() {
    }

    public static Result buildError(String msg) {
        return new Result(400, msg);
    }

    public Result content(T object, Long totalCount) {
        this.setResult(object);
        this.setTotalCount(totalCount);
        return this;
    }

    public Result content(T object) {
        return content(object, null);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
