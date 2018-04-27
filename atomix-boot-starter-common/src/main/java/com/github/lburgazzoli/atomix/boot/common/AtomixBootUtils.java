/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.atomix.boot.common;

import java.util.HashMap;
import java.util.Map;

import io.atomix.cluster.Node;

public final class AtomixBootUtils {
    public static final String META_NODE_ID = "atomix.node.id";
    public static final String META_NODE_TYPE = "atomix.node.type";
    public static final String META_NODE_ZONE = "atomix.node.zone";
    public static final String META_NODE_RACK = "atomix.node.rack";
    public static final String META_NODE_HOST = "atomix.node.host";
    public static final String META_NODE_ADDRESS_HOST = "atomix.node.address.host";
    public static final String META_NODE_ADDRESS_PORT = "atomix.node.address.port";

    private AtomixBootUtils() {
    }

    public static Map<String, String>  getMeta(Node node) {
        final Map<String, String> meta = new HashMap<>();

        putIfNotNull(meta ,META_NODE_ADDRESS_HOST, node.address().host());
        putIfNotNull(meta ,META_NODE_ADDRESS_PORT, Integer.toString(node.address().port()));
        putIfNotNull(meta ,META_NODE_HOST, node.host());
        putIfNotNull(meta ,META_NODE_RACK, node.rack());
        putIfNotNull(meta ,META_NODE_ZONE, node.zone());
        putIfNotNull(meta ,META_NODE_TYPE, node.type().name());
        putIfNotNull(meta ,META_NODE_ID, node.id().id());

        return meta;
    }

    public static <K, V> void putIfNotNull(Map<K, V> map, K key, V val) {
        if (map != null && key != null && val != null) {
            map.put(key, val);
        }
    }
}
