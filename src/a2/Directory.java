// **********************************************************
// Assignment2B:

// Student 1:
// CDF user_name: c5selvar
// UT Student #: 1002344028
// Author: Mathusan Selvarajah
//
// Student 2:
// CDF user_name: c5vijayb
// UT Student #: 1002414167
// Author: Arvinth Vijayanathan
//
// Student 3:
// CDF user_name: c5nabavi
// UT Student #:
// Author: Zaki Nabavi
//
// Student 4:
// CDF user_name: c5dairix
// UT Student #:1002655245
// Author: Rixin Dai
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package a2;

import java.util.*;

/**
 * Directory class is responsible for simulating a Directory object. Directory
 * objects have names and a path. This class is also used to track the paths of
 * child directories of a directory.
 * 
 * @author Mathusan Selvarajah, Rixin Dai
 *
 */
public class Directory extends File {

  private String name;
  // private ArrayList<String> contentPaths; // Stores the paths of the contents
  private String path; // That this directory contains

  /**
   * Constructs a Directory object which is unique due to its name and path
   * 
   * @param name : name of the directory created by the user
   * @param path : path of the directory, this is always stored as an absolute
   *        path
   */
  public Directory(String name, String path) {
    super(name, path);
    // contentPaths = new ArrayList<String>();
  }


  /**
   * Adds a path of an object (File or Directory) that belongs to this directory
   * 
   * @param pathOfObject: A string variable that stores the absolute path of the
   *        directory or file object that is being added to this directory
   * @return: none
   */
  // public void addToDirectory(String pathOfObject) {
  // contentPaths.add(pathOfObject); // Adding to the contentPath ArrayList
  // }


  /**
   * Returns the paths of the objects (File or Directory) that are held by this
   * Directory.
   *
   * @return: ArrayList<String> contentPaths: all paths contained in directory
   */

  public ArrayList<String> getContents() {
    FileSystem fileSystem = FileSystem.createFileSystem();
    Hashtable<String, File> allFiles = fileSystem.getFiles();
    // Goes through the hashtable which contains all paths and
    // finds paths that start with the directory path given
    Set<String> keyset = allFiles.keySet();

    ArrayList<String> allPathsContained = new ArrayList<String>();
    ArrayList<String> sortedKeys = new ArrayList<String>();
    ArrayList<String> contentPaths = new ArrayList<String>();

    // Getting all paths with the given path
    // System.out.println(this.getPath());
    for (String pathKey : keyset) {
      if (pathKey.startsWith(this.getPath())) {
        allPathsContained.add(pathKey);
      }
    }
    // Sorting the paths retrieved
    while (allPathsContained.size() != 0) {
      String temp = allPathsContained.get(0);

      for (int j = 0; j < allPathsContained.size(); j++) {
        if (allPathsContained.get(j).compareTo(temp) < 0) {
          temp = allPathsContained.get(j);
        }
      }

      sortedKeys.add(temp);
      allPathsContained.remove(temp);
    }


    int pathLength = this.getPath().length();
    for (String containedPath : sortedKeys) {
      String temp = containedPath.substring(pathLength);

      if (!temp.contains("/")) {

        contentPaths.add(containedPath);

      } else if (!this.getPath().equals("/")
          && temp.length() - temp.replaceAll("/", "").length() == 1) {
        contentPaths.add(containedPath);
      }
      // String temp = containedPath.substring(pathLength);
      // if (this.getPath().equals("/") && (temp.length() - temp.replaceAll("/",
      // "").length()) == 0 ){
      // contentPaths.add(containedPath);
      //// int nextSlash = containedPath.indexOf("/", pathLength);
      //// contentPaths.add(containedPath.substring(0,nextSlash));
      // }else if (!this.getPath().equals("/") && temp.length() -
      // temp.replaceAll("/", "").length() == 1){
      // contentPaths.add(containedPath);
      // }

    }

    contentPaths.remove(0); // Removes the path of itself
    return contentPaths;
  }

  //// public void removeContent(String path){
  ////
  //// for (int i =0; i < contentPaths.size(); i++){
  //// if (path.equals(contentPaths.get(i)))
  //// contentPaths.remove(i);
  //// }
  //
  //
  // }
}
