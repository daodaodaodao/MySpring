package com.daodao.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseDao<T> extends JpaRepository<T, String>,
		JpaSpecificationExecutor<T> {

}
