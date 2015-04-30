package me.ocs.commons.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 表示一个服务返回结集.
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月28日 下午5:45:20
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean success;
	
	private String message;
	
	private long resultCode;
	
	private Object response;
	
	private Map<String, Object> data;
	
	public Result() {
		this(false);
	}

	public Result(boolean success) {
		super();
		this.success = success;
	}
	
	public static Result newError() {
		return new Result();
	}
	
	public static Result newSuccess() {
		return new Result(true);
	}
	
	public <T> Result callback(ResultCallback callback) {
		callback.doInResult(this);
		return this;
	}
	
	public Result response(Object response) {
		this.response = response;
		return this;
	}
	
	public <T> T getResponse(Class<T> type) {
		return type.cast(response);
	}
	
	public Result data(Map<String, Object> data) {
		if(this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		this.data.putAll(data);
		return this;
	}

	public Result data(String key, Object value) {
		if(data == null) {
			data = new HashMap<String, Object>();
		}
		data.put(key, value);
		return this;
	}
	
	public <T> T getData(String key, Class<T> type) {
		if(data == null) {
			return null;
		}
		return type.cast(data.get(key));
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public Result success(boolean success) {
		setSuccess(success);
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Result message(String message) {
		setMessage(message);
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result resultCode(long resultCode) {
		setResultCode(resultCode);
		return this;
	}

	public long getResultCode() {
		return resultCode;
	}

	public void setResultCode(long resultCode) {
		this.resultCode = resultCode;
	}
}