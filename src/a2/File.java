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

/**
 * File class is the superclass of Directory class and TextFile class It can get
 * name of a file or get path of a file. This is because these things are common
 * amongst File and Directory objects
 * 
 * @author Mathusan Selvarajah, Rixin Dai
 *
 * 
 */
public class File {
  private String name;
  private String path;



  /**
   * Constructs a File object, and even initializes path and name variables for
   * TextFile and Directory objects, when super is called from those classes.
   * 
   * @param name: Stores the name of the File object
   * @param path: Stores the absolute path of the File object
   * 
   * @return: none
   */

  public File(String name, String path) {
    this.path = path;
    this.name = name;
  }

  /**
   * A method used to get the name of a File object. This will work for both
   * Directory or Text file objects, since those classes inherit this File
   * class.
   * 
   * @return: none
   */
  public String getName() {
    String toReturn = null;
    toReturn = this.name;
    return toReturn;
  }

  /**
   * A method used to get the path of a File object. This will work for both
   * Directory or Text file objects, since those classes inherit this File
   * class.
   * 
   * @return none
   */
  public String getPath() {

    String toReturn = "";
    toReturn = this.path;
    return toReturn;
  }

  public void changePath(String path) {
    this.path = path;
  }


}
