package me.ocs.commons.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2014年12月17日 下午11:01:41
 */
public interface GenericDAO<T, PK extends Serializable> extends EntityManagerOperations {

	List<T> queryNative(String sql);

	List<T> queryNative(String sql, Map<String, Object> args);

	List<T> queryNative(String sql, Object[] args);

	T queryNativeForObject(String sql, Object[] args);

	T queryNativeForObject(String sql, Map<String, Object> args);

	List<T> query(String hql);

	List<T> query(String hql, Map<String, Object> args);

	List<T> query(String hql, Object[] args);

	T queryForObject(String hql, Object[] args);

	T queryForObject(String hql, Map<String, Object> args);

	List<T> queryNamed(String queryName);

	List<T> queryNamed(String queryName, Map<String, Object> args);

	List<T> queryNamed(String queryName, Object[] args);

	T queryNamedForObject(String queryName, Map<String, Object> args);

	T queryNamedForObject(String queryName, Object[] args);

	int queryUpdate(String hql, Map<String, Object> args);

	int queryUpdate(String hql, Object[] args);

	int queryNamedUpdate(String queryName, Map<String, Object> args);

	int queryNamedUpdate(String queryName, Object[] args);

	int queryNativeUpdate(String sql, Object[] args);

	int queryNativeUpdate(String sql, Map<String, Object> args);

	T get(PK id);
	
	T getForUpdate(PK id);

	T load(PK id);

	List<T> findAll();
}