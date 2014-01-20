/*
 * Copyright (c) 2013, ReminderPortal, Inc.
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
package org.ccck8ptsa.persistence.entity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.List;

/**
 * IdPEntityUtils.java
 *
 * @author: Russ
 * @since Dec 14, 2013:10:28:08 AM
 */
public class EntityUtils {
    private static Logger logger = LoggerFactory.getLogger(EntityUtils.class);

    public static final Object toPrimaryKeyType(final Class<?> type, final String key) {

        final String typeName = type.getSimpleName();
        try {
            // No conversion necessary
            if (typeName.equals("String")) {
                return key;
            } else if (typeName.equals("Long")) {
                return new Long(key);
            } else if (typeName.equals("Integer")) {
                return new Integer(key);
            } else if (typeName.equals("Double")) {
                return new Double(key);
            } else if (typeName.equals("Float")) {
                return new Float(key);
            } else {
                logger.error("Unsupported type for primary key typeName={}", typeName);
                throw new RuntimeException("Primary Key type of: " + typeName + " not supported");
            }
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Entity ID=" + key + " invalid for entity.  Requires ID of type=" + typeName);
        }
    }

    public static final Object[] toPrimaryKeyType(final Class<?> type, final List<String> keyList) {
        // No conversion needed
        if (type.getSimpleName().equals("String")) {
            return keyList.toArray();
        }
        Object[] o = (Object[]) Array.newInstance(type, keyList.size());
        for (int i = 0; i < keyList.size(); i++) {
            o[i] = toPrimaryKeyType(type, keyList.get(i));

        }
        return o;
    }
}
