package indi.rennnhong.staterkit.core.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.List;

/*
    注意:此接口與spring data jpa框架耦合
 */
public interface Queryable<T> {


    T findOne(@Nullable Specification<T> condition);

    List<T> findAll(@Nullable Specification<T> condition);

    Page<T> findAll(@Nullable Specification<T> condition, Pageable pageable);

    List<T> findAll(@Nullable Specification<T> condition, Sort sort);

    long count(@Nullable Specification<T> condition);

}
