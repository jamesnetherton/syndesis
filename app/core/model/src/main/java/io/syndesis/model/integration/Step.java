/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.model.integration;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.syndesis.model.Kind;
import io.syndesis.model.WithConfiguredProperties;
import io.syndesis.model.WithDependencies;
import io.syndesis.model.WithId;
import io.syndesis.model.WithMetadata;
import io.syndesis.model.action.Action;
import io.syndesis.model.connection.Connection;
import io.syndesis.model.extension.Extension;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = Step.Builder.class)
@SuppressWarnings("immutables")
public interface Step extends WithId<Step>, WithConfiguredProperties, WithDependencies, WithMetadata, Serializable {

    @Override
    default Kind getKind() {
        return Kind.Step;
    }

    Optional<Action> getAction();

    Optional<Connection> getConnection();

    Optional<Extension> getExtension();

    StepKind getStepKind();

    String getName();

    class Builder extends ImmutableStep.Builder {
        // allow access to ImmutableStep.Builder
    }
}
