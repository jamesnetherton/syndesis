/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.syndesis.project.converter.visitor;

import io.syndesis.core.Json;
import io.syndesis.core.SyndesisServerException;
import io.syndesis.model.integration.Step;

import java.io.IOException;
import java.util.HashMap;

public class GenericStepVisitor implements StepVisitor {

    public static final String GENERIC = "generic";

    private final GeneratorContext generatorContext;

    public static class Factory implements StepVisitorFactory<GenericStepVisitor> {

        @Override
        public String getStepKind() {
            return GENERIC;
        }

        @Override
        public GenericStepVisitor create(GeneratorContext generatorContext) {
            return new GenericStepVisitor(generatorContext);
        }
    }

    public GenericStepVisitor(GeneratorContext generatorContext) {
        this.generatorContext = generatorContext;
    }

    @Override
    public io.syndesis.integration.model.steps.Step visit(StepVisitorContext stepContext) {
        try {
            Step step = stepContext.getStep();
            HashMap<String, Object> stepJSON = new HashMap<>(step.getConfiguredProperties().orElse(new HashMap<>()));
            stepJSON.put("kind", step.getStepKind());
            String json = Json.mapper().writeValueAsString(stepJSON);
            return Json.mapper().readValue(json, io.syndesis.integration.model.steps.Step.class);
        } catch (IOException e) {
            throw SyndesisServerException.launderThrowable(e);
        }
    }
}
