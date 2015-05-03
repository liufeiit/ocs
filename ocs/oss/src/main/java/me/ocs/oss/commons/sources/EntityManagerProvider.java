package me.ocs.oss.commons.sources;

import javax.persistence.EntityManager;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年5月3日 下午7:00:13
 */
public interface EntityManagerProvider {
	EntityManager getEntityManager();
}