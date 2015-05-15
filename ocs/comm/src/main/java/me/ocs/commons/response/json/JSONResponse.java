package me.ocs.commons.response.json;

import me.ocs.commons.response.HttpHeader;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 上午12:42:46
 */
public class JSONResponse extends org.springframework.http.ResponseEntity<String> {

	public JSONResponse(JSONResponseBody body, HttpStatus statusCode) {
		super(body.toJson(), HttpHeader.JSON.headers, statusCode);
	}

	public JSONResponse(JSONResponseBody body) {
		this(body, HttpStatus.OK);
	}

	public static JSONResponse newInstance(JSONResponseBody body) {
		return new JSONResponse(body);
	}

	public static JSONResponse newInstance(JSONResponseBody body, HttpStatus statusCode) {
		return new JSONResponse(body, statusCode);
	}
}