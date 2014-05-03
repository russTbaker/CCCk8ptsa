/*
 * Copyright (c) 2014, RBC, LLC.
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

import org.ccck8ptsa.persistence.dao.api.BaseDao;
import org.ccck8ptsa.persistence.entity.BaseEntity;
import org.junit.Ignore;

import javax.validation.ConstraintViolation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

/**
 * BaseDaoTest.java
 *
 * @author: Russ
 * @since Jan 20, 2014:4:27:28 PM
 */
@Ignore
public abstract class BaseDaoTest<E extends BaseEntity, D extends BaseDao> {

        protected void listDelete(D dao) {
        Class<?> type;
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        E tmp1 = doInsert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
        String id1 = tmp1.getId();
        E tmp2 = doInsert();
        String id2 = tmp2.getId();
        List<String> ids = new ArrayList<String>();
        ids.add(id1);
        ids.add(id2);
        dao.delete(type.getSimpleName(), ids);
        for (String id : ids) {
            assertNull(" not deleted", dao.find(id));
        }
    }

    protected void doDelete(D dao) {
        E msg = doInsert();
        String msgId =  msg.getId();
        dao.delete(msgId);
        assertNull("Message not deleted", dao.find(msgId));
    }

    protected Set<ConstraintViolation<E>> validate(D dao,E entity){
        return dao.validate(entity);
    }


    protected abstract <T extends BaseEntity> T doInsert();
}
