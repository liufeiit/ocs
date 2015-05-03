package me.ocs.oss.commons.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.InitializingBean;

import me.ocs.commons.jpa.DefaultGenericDAO;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月3日 下午7:23:48
 */
public class EntityManagerSupportGenericDAO<T, PK extends Serializable> extends DefaultGenericDAO<T, PK> implements InitializingBean {

	@Override
	public EntityManager getEntityManager() {
		return null;
	}
}