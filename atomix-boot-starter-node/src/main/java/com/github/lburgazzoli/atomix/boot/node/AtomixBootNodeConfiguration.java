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
package com.github.lburgazzoli.atomix.boot.node;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import com.github.lburgazzoli.atomix.boot.common.AtomixBootConfiguration;
import io.atomix.protocols.backup.partition.PrimaryBackupPartitionGroupConfig;
import io.atomix.protocols.raft.partition.RaftPartitionGroupConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("atomix.node")
public class AtomixBootNodeConfiguration extends AtomixBootConfiguration {
    enum Profile {
        CONSENSUS(io.atomix.core.profile.Profile.CONSENSUS),
        DATA_GRID(io.atomix.core.profile.Profile.DATA_GRID),
        CLIENT(io.atomix.core.profile.Profile.CLIENT);

        private io.atomix.core.profile.Profile value;

        Profile(io.atomix.core.profile.Profile value) {
            this.value = value;
        }

        io.atomix.core.profile.Profile value() {
            return value;
        }
    }

    private List<Profile> profiles = new ArrayList<>();

    @Valid
    @NestedConfigurationProperty
    private PartitionsGroups partitionGroups = new PartitionsGroups();

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public PartitionsGroups getPartitionGroups() {
        return partitionGroups;
    }


    public void setPartitionGroups(PartitionsGroups partitionGroups) {
        this.partitionGroups = partitionGroups;
    }


    public static class PartitionsGroups {
        /**
         * Raft partitions.
         */
        private List<RaftPartitionGroupConfig> raft = new ArrayList<>();

        /**
         * PrimaryBackup partitions.
         */
        private List<PrimaryBackupPartitionGroupConfig> primaryBackup = new ArrayList<>();

        public List<RaftPartitionGroupConfig> getRaft() {
            return raft;
        }

        public List<PrimaryBackupPartitionGroupConfig> getPrimaryBackup() {
            return primaryBackup;
        }
    }
}
