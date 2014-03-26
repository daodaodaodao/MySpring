package com.daodao.common.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.daodao.common.entity.BaseEntity;
import com.daodao.common.repository.BaseDao;

public abstract class BaseService<T extends BaseEntity> {

	protected abstract BaseDao<T> getDao();

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public JdbcTemplate jdbcTemplate;

	/**
	 * 使用原生态JDBC,进行较复杂分页查询
	 * 
	 * @author Gaochao
	 * @param <T>
	 * @param searchSQL
	 * @param pageNumber
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public Page<Map<String, Object>> getSqlPageInfo(String searchSQL,
			int pageNumber, int pageSize, Object... args) {

		int startIndex = (pageNumber - 1) * pageSize;
		int lastIndex = pageNumber * pageSize;

		StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		paginationSQL.append(searchSQL);
		paginationSQL.append("　) temp where ROWNUM <= " + lastIndex);
		paginationSQL.append(" ) WHERE　num > " + startIndex);

		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(
				paginationSQL.toString(), args);

		String totalSQL = "select count(*) from (" + searchSQL + ")";
		long total = jdbcTemplate.queryForObject(totalSQL, args, Long.class);

		Pageable pr = new PageRequest(pageNumber - 1, pageSize, null);
		Page<Map<String, Object>> p = new PageImpl<Map<String, Object>>(
				resultList, pr, total);

		return p;
	}

	/**
	 * 使用原生态JPA,进行较复杂分页查询 gaochao
	 * 
	 * @param <T>
	 * @param searchHQL
	 * @param totalHQL
	 * @param pageNumber
	 * @param pageSize
	 * @param searchParam
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "hiding" })
	public <T> Page<T> getHqlPageInfo(String searchHQL, String totalHQL,
			int pageNumber, int pageSize, List<Object> searchParam) {

		Query totalQuery = entityManager.createQuery(totalHQL);
		Query searchQuery = entityManager.createQuery(searchHQL);
		searchQuery.setFirstResult((pageNumber - 1) * pageSize);
		searchQuery.setMaxResults(pageSize);

		int i = 1;
		for (Object param : searchParam) {
			searchQuery.setParameter(i, param);
			totalQuery.setParameter(i, param);
			i++;
		}

		Pageable pr = new PageRequest(pageNumber - 1, pageSize, null);
		List<T> resultList = searchQuery.getResultList();
		Number total = (Number) totalQuery.getSingleResult();
		Page<T> p = new PageImpl<T>(resultList, pr, total.longValue());

		return p;
	}


}
