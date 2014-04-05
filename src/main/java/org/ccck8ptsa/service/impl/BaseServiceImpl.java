/*
 * Copyright (c) 2014, RBC, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.ccck8ptsa.service.impl;

import org.ccck8ptsa.persistence.dao.api.BaseDao;
import org.ccck8ptsa.persistence.entity.BaseEntity;
import org.ccck8ptsa.persistence.entity.Event;
import org.ccck8ptsa.service.api.BaseService;
import org.ccck8ptsa.service.exception.ServiceException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * BaseServiceImpl.java
 *
 * @author: Russ
 * @since Apr 5, 2014:10:00:19 AM
 */
public class BaseServiceImpl implements BaseService {
    protected <T extends BaseEntity, D extends BaseDao> Set<ConstraintViolation<T>> validate(T entity, D dao) {
        Set<ConstraintViolation<T>> violations = dao.validate(entity);
        if (!CollectionUtils.isEmpty(violations)) {
            throw new ServiceException(this.renderViolationExceptions(violations));
        }
        return violations;
    }

    protected <T extends BaseEntity> String renderViolationExceptions(Set<ConstraintViolation<T>> violations) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<T> violation : violations) {
            sb.append(violation.getInvalidValue()).append(" ").append(violation.getMessage()).append("\n");
        }
        return sb.toString();
    }

}
