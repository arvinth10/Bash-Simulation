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
 * Copy class resposible for copy objects in the file system, using the
 * helpExecute method from parent class
 * 
 * @author c5vijayb
 *
 */
public class Copy extends ChangeFileSystem {

  /**
   * Default constructor
   */
  public Copy() {

  }

  /**
   * Execute method that calls method in parent class to copy given item
   * 
   * @param allArgs[]: String that contains all arguments given be the user
   * @return: none
   */
  public void execute(String allArgs[]) {
    helpExecute(allArgs);
  }

}
