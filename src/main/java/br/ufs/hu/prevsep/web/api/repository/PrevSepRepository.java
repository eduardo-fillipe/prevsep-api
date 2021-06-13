package br.ufs.hu.prevsep.web.api.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PrevSepRepository<T, ID> extends CrudRepository<T, ID>, QuerydslPredicateExecutor<T> {

}
