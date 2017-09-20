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
 * Keeps track of the current working directory and the stack used in the push
 * and pop methods. Contains the getter and setters for these commands as well.
 * 
 * @author Rixin Dai, c5selvar
 *
 */
public class Handler {

  /**
   * Keeps the current working directory of the file system. Begins with root
   * directory "/"
   */

  private static Handler handlerObj;
  private String currentPath;

  /**
   * The stack used to track the current working directories pushed and popped
   */
  private Stack<String> tracker;


  /**
   * Handler class is using the Singleton Design Pattern
   */
  private Handler() {
    currentPath = "/";
    tracker = new Stack<String>();

  }

  public static Handler createHandler() {
    if (handlerObj == null) {
      handlerObj = new Handler();
    }
    return handlerObj;
  }

  /**
   * Pushes the current working directory onto the stack tracker
   * 
   * @param None
   * @return None
   */
  public void pushPath() {
    tracker.push(currentPath);
  }



  /**
   * Change current working directory to the last path popped on to the stack,
   * and removes that path from the stack
   * 
   * @param None
   * @return None
   */
  public void popPath() {
    currentPath = tracker.pop();
  }

  /**
   * Returns the tracker stack which is used to track the current working
   * directories that are pushed and popped
   * 
   * @param None
   * @return Stack<String> toReturn The tracker stack containing pushed working
   *         directories
   */
  protected Stack<String> getTracker() {
    Stack<String> toReturn = tracker;
    return toReturn;
  }


  /**
   * Getter for the current working directory instance variable
   * 
   * @param None
   * @return String toReturn The current working directory
   */
  public String getCurrentPath() {
    String toReturn = currentPath;
    return toReturn;
  }

  /**
   * Setter for the current working directory instance variable
   * 
   * @param path The path to be set to the current working directory variable
   * @return None
   */
  public void setCurrentPath(String path) {
    currentPath = path;
  }


}
