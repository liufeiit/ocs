package me.ocs.commons.response;

import org.springframework.http.HttpHeaders;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2014年12月27日 上午1:03:31
 */
public enum HttpHeader {
	JSON() {
		@Override
		protected void init() {
			headers = new HttpHeaders();
			headers.set("Content-Type", "application/json;charset=UTF-8");
		}
	},
	XML() {
		@Override
		protected void init() {
			headers = new HttpHeaders();
			headers.set("Content-Type", "application/xml;charset=UTF-8");
		}
	},
	HTML() {
		@Override
		protected void init() {
			headers = new HttpHeaders();
			headers.set("Content-Type", "text/html;charset=UTF-8");
		}
	};
	public HttpHeaders headers;

	protected void init() {
	}
}