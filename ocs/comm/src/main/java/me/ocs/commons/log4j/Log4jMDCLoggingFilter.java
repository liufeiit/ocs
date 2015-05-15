package me.ocs.commons.log4j;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import static me.ocs.commons.log4j.MdcKeys.*;

/**
 * Request logging filter that adds the request log message to the Log4J
 * Mapped Diagnostic Context (MDC) before the request is processed,
 * removing it again after the request is processed.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午11:55:53
 */
public class Log4jMDCLoggingFilter extends AbstractRequestLoggingFilter {
	
	protected final Logger log4jLogger = Logger.getLogger(getClass());

	private static final String REFERER = "Referer";
	private static final String USER_AGENT = "User-Agent";

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		if (log4jLogger.isDebugEnabled()) {
			log4jLogger.debug(message);
		}
		MDC.put(MESSAGE, getNestedDiagnosticContextMessage(request));
		StringBuffer sb = new StringBuffer(request.getRequestURI());
		String queryString = request.getQueryString();
		sb.append(StringUtils.defaultString(queryString, StringUtils.EMPTY));
		MDC.put(REQUEST_URI_WITH_QUERY_STRING, sb);
		MDC.put(REMOTE_ADDR, request.getRemoteAddr());
		MDC.put(REMOTE_HOST, request.getRemoteHost());
		MDC.put(USER_AGENT, request.getHeader(USER_AGENT));
		MDC.put(REFERRER, request.getHeader(REFERER));
		MDC.put(SESSION_ID, request.getRequestedSessionId());
		MDC.put(LOGIN_USER_NICK, getLoginUserNick(request));
	}
	
	protected String getNestedDiagnosticContextMessage(HttpServletRequest request) {
		return createMessage(request, "", "");
	}
	
	private String getLoginUserNick(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(LOGIN_USER_NICK);
		if(obj == null) {
			return ANONYMOUS;
		}
		return StringUtils.defaultIfBlank(String.valueOf(obj), ANONYMOUS);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		MDC.getContext().clear();
		if (log4jLogger.isDebugEnabled()) {
			log4jLogger.debug(message);
		}
	}
}