JSONNET_FMT := jsonnet fmt -n 2 --max-blank-lines 2 --string-style s --comment-style s
JB_BINARY:=$(GOPATH)/bin/jb

all: vendor build fmt

vendor: $(JB_BINARY) jsonnetfile.json jsonnetfile.lock.json
	rm -rf vendor
	$(JB_BINARY) install

fmt:
	find . -name 'vendor' -prune -o -name '*.libsonnet' -o -name '*.jsonnet' -print | xargs -n 1 -- $(JSONNET_FMT) -i

build:
	rm -rf generated/*
	./build.sh

$(JB_BINARY):
	go get -u github.com/jsonnet-bundler/jsonnet-bundler/cmd/jb

.PHONY: all
