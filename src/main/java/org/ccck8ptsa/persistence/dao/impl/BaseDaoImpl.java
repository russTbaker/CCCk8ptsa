/*
 * Copyright (c) 2013, RBC, LLC.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the RBC, LLC. nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL RBC, LLC. BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.ccck8ptsa.persistence.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.ccck8ptsa.persistence.dao.api.BaseDao;
import org.ccck8ptsa.persistence.entity.BaseEntity;
import org.ccck8ptsa.persistence.entity.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * BaseDaoImpl.java
 *
 * @author: Russ
 * @since Jan 20, 2014:12:44:42 PM
 */
public class BaseDaoImpl<T, PK> implements BaseDao<T, PK> {
    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;



    public BaseDaoImpl() {
        super();
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(final PK pk) {
        return this.em.find(type, pk);
    }

    public T update(final T entity) {
        this.em.merge(entity);
        return entity;
    }

    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    public T create(final T entity) {
        this.em.persist(entity);
        this.em.flush();
        return entity;
    }

    @Override
    public void delete(String entityType, List<String> idList) {
        EntityType<?> entity = getValidEntity(entityType);
        List<BaseEntity> deletable = (List<BaseEntity>) findByPrimaryKey(entity, idList);
        for (BaseEntity toDelete : deletable) {
            if (isManagedEntity(entity)) {
                delete(toDelete.getId());
            }
        }
    }

    private List<T> findByPrimaryKey(EntityType<?> entity, List<String> idList) {
        List<Predicate> conditions = new ArrayList<Predicate>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Class<BaseEntity> clazz = (Class<BaseEntity>) entity.getJavaType();
        CriteriaQuery query = cb.createQuery(clazz);
        Root from = query.from(clazz);
        from.alias(clazz.getSimpleName());

        Class<?> pkType = entity.getIdType().getJavaType();

        if (idList.size() == 1) {
            conditions.add(cb.equal(from.get("id"),
                    EntityUtils.toPrimaryKeyType(pkType, idList.get(0))));
        } else {
            conditions.add(from.get("id").as(pkType).
                    in(EntityUtils.toPrimaryKeyType(pkType, idList)));
        }
        query.where(conditions.toArray(new Predicate[]{}));
        return em.createQuery(query).getResultList();
    }

    private boolean isManagedEntity(EntityType<?> entityType) {
        return BaseEntity.class.isAssignableFrom(entityType.getJavaType());
    }

    //TODO: Need a way to find parent entity and get PK fields from that

    private EntityType<?> getValidEntity(String entityName) {
        if (StringUtils.isBlank(entityName)) {
            logger.error("error: Invalid entity! Entity name must not be empty {}", entityName);
            throw new IllegalArgumentException("Entity type required for fetch()");
        }

        Set<EntityType<?>> entityTypes = em.getMetamodel().getEntities();
        for (EntityType<?> entityType : entityTypes) {
            String managedClassName = entityType.getJavaType().getSimpleName().toLowerCase();
            if (managedClassName.equals(entityName.toLowerCase())) {
                return entityType;
            }
        }
        logger.error("error: Can not find entity {}", entityName);
        throw new RuntimeException("Unknown entity=" + entityName);
    }

    public Set<ConstraintViolation<T>> validate(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(entity);
    }
}
