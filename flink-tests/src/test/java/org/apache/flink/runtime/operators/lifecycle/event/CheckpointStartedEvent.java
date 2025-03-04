/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.operators.lifecycle.event;

import org.apache.flink.annotation.Internal;
import org.apache.flink.runtime.state.StateSnapshotContext;
import org.apache.flink.streaming.api.operators.AbstractStreamOperator;

import java.util.Objects;

/**
 * An event of calling {@link AbstractStreamOperator#snapshotState(StateSnapshotContext)
 * snapshotState} method on an operator.
 */
@Internal
public class CheckpointStartedEvent extends TestEvent {
    public final long checkpointID;

    public CheckpointStartedEvent(String operatorId, int subtaskIndex, long checkpointID) {
        super(operatorId, subtaskIndex);
        this.checkpointID = checkpointID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckpointStartedEvent)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CheckpointStartedEvent that = (CheckpointStartedEvent) o;
        return checkpointID == that.checkpointID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), checkpointID);
    }

    @Override
    public String toString() {
        return super.toString() + "(" + checkpointID + ")";
    }
}
