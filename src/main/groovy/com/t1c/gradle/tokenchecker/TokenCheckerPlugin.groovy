package com.t1c.gradle.tokenchecker;

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

import groovy.io.FileType

class TokenCheckerPlugin implements Plugin<Project> {
  void apply(Project project) {
    // Add the 'tokenchecker' extension object
    project.extensions.create("tokencheckerOptions", TokenCheckerOptions)

    //main task
    project.task('checkTokens') { doLast { matchTemplates(project) } }
  }


  def matchTemplates(Project project) {
    println "\tSearching for tokens ..."
    def success = true;
    project.tokencheckerOptions.SEARCH_LOCATIONS.each {
      def dir = new File("${it}")
      println "${it}"
      dir.eachFileRecurse (FileType.FILES) { file ->
        project.tokencheckerOptions.PATTERNS.each { pattern ->
          def test = new TokenCheckerPattern(regex: pattern);
          if(test.match(file)){
            success = false
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