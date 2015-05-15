package me.ocs.commons.log4j;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年5月15日 下午6:28:18
 */
public interface MdcKeys {
	String REFERRER = "referrer";
	String USER_AGENT = "userAgent";
	String REMOTE_HOST = "remoteHost";
	String REMOTE_ADDR = "remoteAddr";
	String REQUEST_URI_WITH_QUERY_STRING = "requestURIWithQueryString";
	String MESSAGE = "message";
	String SESSION_ID = "sessionId";
	
	String LOGIN_USER_NICK = "loginUserNick";
	String ANONYMOUS = "Anonymous";
}