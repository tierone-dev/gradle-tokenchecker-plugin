package com.t1c.gradle.tokenchecker;

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

import groovy.io.FileType

class TokenCheckerPlugin implements Plugin<Project> {
  void apply(Project project) {
    // Add the 'tokenchecker' extension object
    project.extensions.create("tokencheckerOptions", TokenCheckerPluginExtension)

    //main task
    project.task('policeTokens') { doLast { checkInvalidTemplates(project) } }
  }


  def checkInvalidTemplates(Project project) {
    println "\tValidating html/angular tags ..."
    def success = true;
    project.tokencheckerOptions.SEARCH_LOCATIONS.each {
      def dir = new File("${it}")
      dir.eachFileRecurse (FileType.FILES) { file ->
        project.tokencheckerOptions.INVALID_PATTERNS.each { pattern ->
          file.eachLine { line, lineNumber ->
            if(line.matches(pattern)) {
              println "\t------------------------"
              println "\tFound Forbidden Token "
              println "\tToken: " + pattern
              println "\tIn File: " + file.name
              println "\t@Line: " + lineNumber
              println "\t------------------------"
              success = false
            }
          }

        }
      }
    }
    if(success) {
      println "\tDone Validating..."
    } else {
      println "\tValidation Failed... [ABORTING]"
      throw new GradleException("Validation Failed")
    }

  }
}

class TokenCheckerPluginExtension {
  String[] SEARCH_LOCATIONS
  String[] INVALID_PATTERNS
}