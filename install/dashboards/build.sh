#!/bin/bash

# Generates grafana dashboard JSON for each jsonnet template
for DASHBOARD in templates/*.jsonnet; do
    echo "${DASHBOARD##*.}.json"
    jsonnet -J vendor ${DASHBOARD} -o generated/${DASHBOARD##*.}.json
done
