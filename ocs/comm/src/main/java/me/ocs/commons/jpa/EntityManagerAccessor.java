package me.ocs.commons.jpa;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 封装EntityManager相关操作, 更容易使用。
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年1月1日 下午11:01:05
 */
public abstract class EntityManagerAccessor implements EntityManagerOperations, InitializingBean {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Query createQuery(String hql) {
		return getEntityManager().createQuery(hql);
	}

	@Override
	public Query createQuery(String hql, Map<String, Object> args) {
		return queryArgs(getEntityManager().createQuery(hql), args);
	}

	@Override
	public Query createQuery(String hql, Object[] args) {
		return queryArgs(getEntityManager().createQuery(hql), args);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String hql, Class<T> clazz) {
		return getEntityManager().createQuery(hql, clazz);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String hql, Class<T> clazz, Map<String, Object> args) {
		return queryArgs(getEntityManager().createQuery(hql, clazz), args);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String hql, Class<T> clazz, Object[] args) {
		return queryArgs(getEntityManager().createQuery(hql, clazz), args);
	}

	@Override
	public Query createNamedQuery(String queryName) {
		return getEntityManager().createNamedQuery(queryName);
	}

	@Override
	public Query createNamedQuery(String queryName, Map<String, Object> args) {
		return queryArgs(getEntityManager().createNamedQuery(queryName), args);
	}

	@Override
	public Query createNamedQuery(String queryName, Object[] args) {
		return queryArgs(getEntityManager().createNamedQuery(queryName), args);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String queryName, Class<T> clazz) {
		return getEntityManager().createNamedQuery(queryName, clazz);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String queryName, Class<T> clazz, Map<String, Object> args) {
		return queryArgs(getEntityManager().createNamedQuery(queryName, clazz), args);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String queryName, Class<T> clazz, Object[] args) {
		return queryArgs(getEntityManager().createNamedQuery(queryName, clazz), args);
	}

	@Override
	public Query createNativeQuery(String sql) {
		return getEntityManager().createNativeQuery(sql);
	}

	@Override
	public Query createNativeQuery(String sql, Map<String, Object> args) {
		return queryArgs(getEntityManager().createNativeQuery(sql), args);
	}

	@Override
	public Query createNativeQuery(String sql, Object[] args) {
		return queryArgs(getEntityManager().createNativeQuery(sql), args);
	}

	@Override
	public Query createNativeQuery(String sql, Class<?> clazz) {
		return getEntityManager().createNativeQuery(sql, clazz);
	}

	@Override
	public Query createNativeQuery(String sql, Class<?> clazz, Map<String, Object> args) {
		return queryArgs(getEntityManager().createNativeQuery(sql, clazz), args);
	}

	@Override
	public Query createNativeQuery(String sql, Class<?> clazz, Object[] args) {
		return queryArgs(getEntityManager().createNativeQuery(sql, clazz), args);
	}

	@Override
	public <T> T persist(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public <T> void remove(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public <T> T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public <T, PK> T get(Class<T> clazz, PK primaryKey) {
		return getEntityManager().find(clazz, primaryKey);
	}

	@Override
	public <T, PK> T get(Class<T> clazz, PK primaryKey, LockModeType lockMode) {
		return getEntityManager().find(clazz, primaryKey, lockMode);
	}

	@Override
	public <T, PK> T load(Class<T> clazz, PK primaryKey) {
		return getEntityManager().getReference(clazz, primaryKey);
	}

	@Override
	public <T> T refresh(T entity) {
		getEntityManager().refresh(entity);
		return entity;
	}

	@Override
	public <T> T refresh(T entity, LockModeType lockMode) {
		getEntityManager().flush();
		getEntityManager().refresh(entity, lockMode);
		return entity;
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	@Override
	public void clear() {
		getEntityManager().clear();
	}

	@Override
	public final void afterPropertiesSet() throws Exception {
		init();
	}

	protected void init() throws Exception {

	}

	protected final <Q extends Query> Q queryArgs(Q query, Map<String, Object> args) {
		if (query == null || args == null || args.isEmpty()) {
			return query;
		}
		for (Map.Entry<String, Object> entry : args.entrySet()) {
			if (entry.getValue() instanceof java.util.Date) {
				query.setParameter(entry.getKey(), (java.util.Date) entry.getValue(), TemporalType.TIMESTAMP);
				continue;
			}
			if (entry.getValue() instanceof java.sql.Date) {
				query.setParameter(entry.getKey(), (java.sql.Date) entry.getValue(), TemporalType.TIMESTAMP);
				continue;
			}
			if (entry.getValue() instanceof java.util.Calendar) {
				query.setParameter(entry.getKey(), (java.util.Calendar) entry.getValue(), TemporalType.TIMESTAMP);
				continue;
			}
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}

	protected final <Q extends Query> Q queryArgs(Q query, Object[] args) {
		if (query == null || args == null || args.length <= 0) {
			return query;
		}
		int position = 1;
		for (Object value : args) {
			if (value instanceof java.util.Date) {
				query.setParameter(position++, (java.util.Date) value, TemporalType.TIMESTAMP);
				continue;
			}
			if (value instanceof java.sql.Date) {
				query.setParameter(position++, (java.sql.Date) value, TemporalType.TIMESTAMP);
				continue;
			}
			if (value instanceof java.util.Calendar) {
				query.setParameter(position++, (java.util.Calendar) value, TemporalType.TIMESTAMP);
				continue;
			}
			query.setParameter(position++, value);
		}
		return query;
	}

	public abstract EntityManager getEntityManager();
}