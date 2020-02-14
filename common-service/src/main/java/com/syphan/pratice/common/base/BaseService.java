package com.syphan.pratice.common.base;

import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity, ID extends Serializable> {
    public T save(T entity);

    public T getById(ID id);

    public List<T> getAllById(Iterable<ID> ids);

    public List<T> listAll(Sort sort);
}
