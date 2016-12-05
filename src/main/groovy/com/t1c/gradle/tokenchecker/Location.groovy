package com.t1c.gradle.tokenchecker
import groovy.io.FileType

class Location {
  File root
  String[] ignored

  boolean search(String[] patterns){
    boolean result = false
    root.eachFileRecurse (FileType.FILES) { file ->
      if(!ignored.contains(file.name)){
        patterns.each { pattern ->
          def test = new TokenCheckerPattern(regex: pattern)
          if(test.match(file)){
            result = true
          }
        }
      }
    }
    return result
  }
}
