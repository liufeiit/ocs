package me.ocs.commons.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

/**
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月29日 上午10:57:59
 */
@SuppressWarnings("unchecked")
public abstract class DefaultGenericDAO<T, PK extends Serializable> extends EntityManagerAccessor implements GenericDAO<T, PK> {

	protected final Class<T> entityType;

	public DefaultGenericDAO() {
		entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public List<T> queryNative(String sql, Map<String, Object> args) {
		return createNativeQuery(sql, getEntityType(), args).getResultList();
	}

	@Override
	public List<T> queryNative(String sql, Object[] args) {
		return createNativeQuery(sql, getEntityType(), args).getResultList();
	}

	@Override
	public T queryNativeForObject(String sql, Object[] args) {
		try {
			return (T) createNativeQuery(sql, getEntityType(), args).getSingleResult();
		} catch (javax.persistence.NoResultException ingore) {}
		return null;
	}

	@Override
	public T queryNativeForObject(String sql, Map<String, Object> args) {
		try {
			return (T) createNativeQuery(sql, getEntityType(), args).getSingleResult();
		} catch (javax.persistence.NoResultException ingore) {}
		return null;
	}

	@Override
	public List<T> queryNative(String hql) {
		return createNativeQuery(hql, getEntityType()).getResultList();
	}

	@Override
	public List<T> query(String hql) {
		return createQuery(hql, getEntityType()).getResultList();
	}

	@Override
	public List<T> query(String hql, Map<String, Object> args) {
		return createQuery(hql, getEntityType(), args).getResultList();
	}

	@Override
	public List<T> query(String hql, Object[] args) {
		return createQuery(hql, getEntityType(), args).getResultList();
	}

	@Override
	public T queryForObject(String hql, Object[] args) {
		try {
			return createQuery(hql, getEntityType(), args).getSingleResult();
		} catch (javax.persistence.NoResultException ingore) {}
		return null;
	}

	@Override
	public T queryForObject(String hql, Map<String, Object> args) {
		try {
			return createQuery(hql, getEntityType(), args).getSingleResult();
		} catch (javax.persistence.NoResultException ingore) {}
		return null;
	}

	@Override
	public List<T> queryNamed(String queryName) {
		return createNamedQuery(queryName, getEntityType()).getResultList();
	}

	@Override
	public List<T> queryNamed(String queryName, Map<String, Object> args) {
		return createNamedQuery(queryName, getEntityType(), args).getResultList();
	}

	@Override
	public List<T> queryNamed(String queryName, Object[] args) {
		return createNamedQuery(queryName, getEntityType(), args).getResultList();
	}

	@Override
	public T queryNamedForObject(String queryName, Map<String, Object> args) {
		try {
			return createNamedQuery(queryName, getEntityType(), args).getSingleResult();
		} catch (javax.persistence.NoResultException ingore) {}
		return null;
	}

	@Override
	public T queryNamedForObject(String queryName, Object[] args) {
		try {
			return createNamedQuery(queryName, getEntityType(), args).getSingleResult();
		} catch (javax.persistence.NoResultException ingore) {}
		return null;
	}

	@Override
	public T get(PK id) {
		return getEntityManager().find(getEntityType(), id);
	}

	@Override
	public T getForUpdate(PK id) {
		getEntityManager().flush();
		return getEntityManager().find(getEntityType(), id, LockModeType.PESSIMISTIC_WRITE);
	}

	@Override
	public T load(PK id) {
		return getEntityManager().getReference(getEntityType(), id);
	}

	@Override
	public List<T> findAll() {
		return query("FROM " + entityType.getName());
	}

	@Override
	public int queryUpdate(String hql, Map<String, Object> args) {
		return createQuery(hql, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryUpdate(String hql, Object[] args) {
		return createQuery(hql, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNamedUpdate(String queryName, Map<String, Object> args) {
		return createNamedQuery(queryName, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNamedUpdate(String queryName, Object[] args) {
		return createNamedQuery(queryName, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNativeUpdate(String sql, Object[] args) {
		return createNativeQuery(sql, getEntityType(), args).executeUpdate();
	}

	@Override
	public int queryNativeUpdate(String sql, Map<String, Object> args) {
		return createNativeQuery(sql, getEntityType(), args).executeUpdate();
	}

	protected Class<T> getEntityType() {
		return entityType;
	}
}