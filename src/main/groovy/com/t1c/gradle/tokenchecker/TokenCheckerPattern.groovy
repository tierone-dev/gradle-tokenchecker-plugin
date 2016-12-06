package com.t1c.gradle.tokenchecker

class TokenCheckerPattern{
  String regex

  boolean match(File file){
    def found = false
    file.eachLine { line, lineNumber ->
      if(line.matches(regex)) {
        println "\n\t------------------------"
        println "\tFound Match "
        println "\tToken: " + regex
        println "\tIn File: " + file.name
        println "\t@Line: " + lineNumber
        found = true;
      }
    }
    return found
  }
}