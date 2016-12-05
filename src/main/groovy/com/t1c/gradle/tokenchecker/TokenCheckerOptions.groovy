package com.t1c.gradle.tokenchecker

class TokenCheckerOptions {
  /**
   * Array of directory paths to include in the search.
   * ex: String[] SEARCH_LOCATIONS = [ "/home/user/development/git/MyProj/src/main/MyApp/DirToInclude" , "../src/main/OtherDir" ]
   */
  String[] SEARCH_LOCATIONS

  /**
   * Array of patterns checker shall match.
   * ex: String[] PATTERNS = [ /(.*)ng-include(.*)/ , /(.*)<img(.*)/  ]
   */
  String[] PATTERNS

  /**
   * Array of file names to ignore.
   * ex: String[] PATTERNS = [ "myfile.ext" , "index.jsp"  ]
   */
  String[] IGNORED
}
