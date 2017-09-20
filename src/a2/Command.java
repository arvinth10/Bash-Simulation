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

import java.util.Hashtable;

/**
 * Parent class for all commands, and manipulates paths so it can be easily
 * worked with.
 * 
 * @author c5vijab
 *
 */
public class Command {

  /**
   * Default Constructor
   */
  protected Handler handler;
  protected FileSystem fileSystem;

  public Command() {
    handler = Handler.createHandler();
    fileSystem = FileSystem.createFileSystem();
  }

  /**
   * Executes the command. This method will be overwritten in all other command
   * classes
   * 
   * @param allArgs[] Contains all arguments of the command
   * @return None
   */

  public void execute(String allArgs[]) {
    System.out.println("Called from command class");

  }

  protected String toAbsolutePath(String path) {
    if (path.startsWith("/"))
      return path;
    else if (handler.getCurrentPath().equals("/"))
      return "/" + path;
    else
      return handler.getCurrentPath() + "/" + path;
  }

  /**
   * Manipulates the path so it can be easily worked with in the system. Removes
   * frontslashes at the end of paths, removes backslashes from path, and takes
   * care of the dots
   *
   * @param String path The path to be manipulated
   * @return String path The path after changes has been made
   */
  protected String pathChanger(String path) {
    String first;
    path = path.replaceAll("\\/+", "/");
    int firstSlash;
    if (path.endsWith("/") && !path.equals("/")) // Remove frontslashes at end
                                                 // of path
      path = path.substring(0, path.length() - 1);

    path = path.replaceAll("\\\\", ""); // Remove all backslashes

    if (path.contains("/"))
      firstSlash = path.indexOf("/");
    else
      firstSlash = path.length() - 1;

    first = path.substring(0, firstSlash); // First portion of path

    if (first.length() - first.replaceAll(".", "").length() == first.length()) {
      if (path.startsWith("..")) {
        // To parent directory if starts with double dots
        String pathEnding = path.substring(firstSlash + 1);
        path = Cd.toParentDirectory(handler.getCurrentPath());
        if (path.equals("/"))
          path = path + pathEnding;
        else
          path = path + "/" + pathEnding;
        // All dots in first portion are removed
      } else if (path.startsWith("."))
        path = path.substring(firstSlash + 1);
    }
    return path;

  }

}

