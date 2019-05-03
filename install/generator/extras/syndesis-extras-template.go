// To install, run:
//
//   go get -u github.com/spf13/cobra github.com/spf13/pflag github.com/hoisie/mustache
//   go install syndesis-template.go
//
// Or to just run without installing:
//
//   go run syndesis-template.go <args>
//
package main

import (
	//"flag"

	"fmt"
	"io/ioutil"
	"regexp"
	"strings"

	"github.com/hoisie/mustache"
	"github.com/spf13/cobra"
)

func main() {
	err := installCommand.Execute()
	check(err)
}

var installCommand = &cobra.Command{
	Use:   "syndesis-extras-template",
	Short: "syndesis-extras-template is a tool for creating the OpenShift templates used to install Syndesis extras.",
	Long:  `syndesis-extras-template is a tool for creating the OpenShift templates used to install Syndesis extras.`,
	Run:   install,
}

type ExtrasResource struct {
	FileName string
	Content  string
}

type Context struct {
	ServiceMonitors []ExtrasResource
	PrometheusRules []ExtrasResource
	Dashboards      []ExtrasResource
}

var syndesisContext = Context{}

var context = syndesisContext

func install(cmd *cobra.Command, args []string) {
	context.ServiceMonitors = getContextResources("../addons/", "servicemonitor.yml", 2)
	context.PrometheusRules = getContextResources("../addons/", "prometheus-rule.yml", 2)
	context.Dashboards = getContextResources("./extras/dashboards/", ".json", 6)

	template, err := ioutil.ReadFile("./extras/syndesis-monitoring.yml.mustache")
	check(err)
	fmt.Print(mustache.Render(string(template), context))
}

func getContextResources(directory string, suffixMatch string, indentCount int) []ExtrasResource {
	files, err := ioutil.ReadDir(directory)
	check(err)

	var resources []ExtrasResource
	var regex = regexp.MustCompile("(?m)(.*)$")
	var indent = strings.Repeat(" ", indentCount)

	for _, file := range files {
		if strings.HasSuffix(file.Name(), suffixMatch) {
			bytes, err := ioutil.ReadFile(directory + file.Name())
			check(err)

			content := regex.ReplaceAllString(string(bytes), indent+"$1")

			if strings.HasSuffix(suffixMatch, ".yml") {
				content = strings.Replace(content, "  apiVersion", "apiVersion", -1)
				content = strings.Replace(content, "spec:", "  namespace: ${FUSE_ONLINE_NAMESPACE}\n  spec:", 1)
			}

			resource := ExtrasResource{
				FileName: file.Name(),
				Content:  content,
			}
			resources = append(resources, resource)
		}
	}

	return resources
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}
