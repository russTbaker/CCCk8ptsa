package org.ccck8ptsa.persistence.dao.api;

import java.util.List;

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
}
