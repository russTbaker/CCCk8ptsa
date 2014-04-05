package org.ccck8ptsa.persistence.dao.api;

import org.ccck8ptsa.persistence.entity.BaseEntity;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * BaseDao.java
 *
 * @author: Russ
 * @since Jan 20, 2014:12:43:32 PM
 */
public interface BaseDao<T,PK> {
    T find(PK pk);

    T update(T entity);

    void delete(Object id);

    T create(T entity);

    void delete(String entityType, List<String> idList);

    Set<ConstraintViolation<BaseEntity>> validate(BaseEntity entity);
}
