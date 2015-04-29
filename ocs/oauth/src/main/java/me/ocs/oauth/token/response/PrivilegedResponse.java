package me.ocs.oauth.token.response;

import me.ocs.oauth.token.Alias;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年2月5日 下午3:01:24
 */
@XStreamAlias(Alias.Privileged_Response)
public class PrivilegedResponse {
	
	public static final PrivilegedResponse DEFAULT_RESPONSE = new PrivilegedResponse(StringUtils.EMPTY);
	
	/**
	 * 应用系统用户ID
	 */
	@XStreamAlias(Alias.SECRET_ID)
	private String secret_id;

	public PrivilegedResponse(String secret_id) {
		super();
		this.secret_id = secret_id;
	}

	public String getSecret_id() {
		return secret_id;
	}

	public void setSecret_id(String secret_id) {
		this.secret_id = secret_id;
	}
}