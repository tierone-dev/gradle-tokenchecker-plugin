package com.t1c.gradle.tokenchecker

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class TokenCheckerPlugin implements Plugin<Project> {
  void apply(Project project) {
    // Add the 'tokenchecker' extension object
    project.extensions.create("tokencheckerOptions", TokenCheckerOptions)

    //main task
    project.task('checkTokens') { doLast { matchTemplates(project) } }
  }


  def matchTemplates(Project project) {
    print "\tSearching for tokens ..."
    def success = true
    project.tokencheckerOptions.SEARCH_LOCATIONS.each {
      def location = new Location(root: new File(it), ignored: project.tokencheckerOptions.IGNORED)
      if(location.search(project.tokencheckerOptions.PATTERNS)){
        success = false
      }
    }
    if(success) {
      print "[DONE]\n"
    } else {
      println "\tValidation Failed... [ABORTING]"
      throw new GradleException("Validation Failed")
    }

  }
}