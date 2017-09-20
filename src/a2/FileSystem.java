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
 * Responsible for keeping track of the entire file system. Can add new objects
 * to the file system and used to access the main Hashtable holding all objects
 * created
 * 
 * @author c5vijayb
 *
 */
public class FileSystem {

  private static FileSystem fileSystem;

  /**
   * Hashtable stores all fileObjects created in the FileSystem, including
   * directories and text files.
   */
  private Hashtable<String, File> fileObjects;
  private Handler handler;

  /**
   * File System is using the Singleton Design Pattern Creates a file system,
   * used to initialize the root directory
   * 
   * @param None
   * @return None
   */
  private FileSystem() {
    fileObjects = new Hashtable<String, File>();
    fileObjects.put("/", new Directory("/", "/"));
    handler = Handler.createHandler();
  }

  /**
   * File System is using the Singleton Design Pattern So this static method is
   * used to initialize the first object
   *
   * @return None
   */
  public static FileSystem createFileSystem() {
    if (fileSystem == null) {
      fileSystem = new FileSystem();
    }
    return fileSystem;
  }

  /**
   * Given a path, an array is returned containing all the essential information
   * to create a new Object. This includes name of new object, path, and path of
   * directory adding new object to.
   * 
   * @param String path A relative or absolute path of new object
   * @return String objectValues [] Array of information need to create new
   *         object
   */

  protected String[] newObject(String path) {

    // All examples to add Assignement2A directory/file with all other
    // directories shown already created

    String objectValues[] = new String[3];
    int lastSlash = path.lastIndexOf("/");
    if ((path.length() - path.replaceAll("/", "").length()) == 1) {
      // Example: CurrentWorkingDirectory = Anything
      // path = /Assignement2A

      objectValues[0] = path.substring(1);
      objectValues[1] = path;
      objectValues[2] = "/";

    } else if (fileObjects.containsKey(path.substring(0, lastSlash))) {
      // Example: CurrentWorkingDirectory = Anything
      // path = /Group_0601/ComputerScience/207/Assignement2A

      objectValues[0] = path.substring(lastSlash + 1, path.length());
      objectValues[1] = path;
      objectValues[2] = path.substring(0, lastSlash);


    } else {
      objectValues[0] = "Error";
      System.out.println("Invalid path exception");
    }
    return objectValues;
  }

  /**
   * Adds a given file, either Directory or TextFile, to the file system
   * hashtable
   * 
   * @param key A String of the path being added
   * @param file A object File given to be added to the hashtable
   * @return None
   */

  public void addFile(String key, Object file) {

    if (file instanceof Directory) {
      fileObjects.put(key, (Directory) (file));
    } else {
      fileObjects.put(key, (TextFile) (file));
    }

    // ((Directory)
    // fileObjects.get(fileValues[2])).addToDirectory(fileValues[1]);
    // Downcasting the file object to a directory,
    // Since fileObjects.get(fileValues[2]) will always return a directory

  }

  public void removeFile(String key) {
    fileObjects.remove(key);
  }

  /**
   * Checks if there is a directory or text file at the given path
   * 
   * @param path An absolute path given to check
   * @return True or False based on if path exists
   */

  // public static boolean checkIfPathExists(String path) {
  // if (fileObjects.containsKey(path)) {
  // return true;
  // } else {
  // return false;
  // }
  //
  // }

  /**
   * Return the hashtable of all file objects used to keep track of all items in
   * the file system
   * 
   * @param None
   * @return fileObjects The hashtable of all file objects
   */

  public Hashtable<String, File> getFiles() {
    return fileObjects;
  }

  /**
   * Sets the main hashtable in the file system to the given one
   * 
   * @param set A hashtable used to set main Hashtable
   * @return None
   */

  public void setFiles(Hashtable<String, File> set) {
    fileObjects = set;
  }

}
