package me.ocs.commons.log4j;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月14日 下午11:55:53
 */
public class Log4jMDCLoggingFilter extends AbstractRequestLoggingFilter {

	protected final Logger log4jLogger = Logger.getLogger(getClass());

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		MDC.put("message", message);
		StringBuffer sb = new StringBuffer(request.getRequestURI());
		String queryString = request.getQueryString();
		sb.append(StringUtils.defaultString(queryString, StringUtils.EMPTY));
		MDC.put("requestURIWithQueryString", sb);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {

	}
}