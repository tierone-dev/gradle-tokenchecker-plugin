# gradle-tokenchecker-plugin
A simple totaly configurable token checker plugin for gradle written in groovy

## Tasks

The plugin adds the following tasks:

### - checkTokens

Check for tokens in files using tokencheckerOptions.

## Installation

* Clone or download project 
* Execute `gradle uploadArchives` to add project to local repositories
* Add the plugin like this:

[code, lang=groovy]
----
apply plugin: "com.t1c.gradle.tokenchecker.plugin"

buildscript {
  repositories {
    mavenLocal()
  }
  dependencies {
    classpath "com.t1c.gradle.tokenchecker:gradle-tokenchecker-plugin:1.0.0"
  }
}
----

## Configuration

General configuration for the plugin goes inside a `tokencheckerOptions` plain groovy object in your build file and will applied to all tasks. For example:

[code, lang=groovy]
----
tokencheckerOptions { 
    SEARCH_LOCATIONS = [ "/home/user/project/src/modules" , "../src/vendor/t1c/" ]
    PATTERNS = [ /(.*)ng-include(.*)/ , /(.*)<img(.*)/  ]
    IGNORED = ["my_file.js" , "index.jsp"]
}
----

### Configuration parameters

Parameters supported by the plugin is:

* `SEARCH_LOCATIONS`: a string list with the source directories where you want to search.
* `PATTERNS`: must pass a java/python regex array of patterns to match.
* `IGNORED`: string list of file names to be globaly ignored.
