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

import java.util.Hashtable;

/**
 * Stderr class is responsible for all error related output
 * 
 * @author Mathusan Selvarajah
 *
 */
public class Stderr {

  private Hashtable<Integer, String> errors = new Hashtable<Integer, String>();

  /**
   * Constructs an stderr (Standard error output object) object. And Initializes
   * the Hash Table that contains all possible errors.
   */
  public Stderr() {
    // Add all possible error messages as value, with a number representing the
    // error message
    errors.put(1, "Invalid command, please try again");
    errors.put(2, "Directory already exists, please try again");
    errors.put(3, "Invalid path, please try again");
    errors.put(4, "No manual for such command");
    errors.put(5, " is a directory");
    errors.put(6, "stack is empty");
    errors.put(7, " is a text file");
    errors.put(8, "invalid number");
    errors.put(9, "invalid string");
    errors.put(10, "Can not move/copy directory into a text file");
    errors.put(11, "invalid number");
    errors.put(12, "invalid URL");
    errors.put(13, "Cant move a parent directory to subdirectory exception");
  }

  /**
   * Print method, simply prints the error message that corresponds to the given
   * number
   * 
   * @param num: The number key that is used to get the error output from the
   *        hash table containing all error outputs.
   */
  public void print(int num) {
    System.out.println(errors.get(num));
  }

  /**
   * An overloaded error method that does the same thing as the above method but
   * specifies from which command the error is coming from.
   * 
   * @param commandName: A string that represents the command name
   * @param num: The number key that is used to get the error output from the
   *        hash table containing all error outputs.
   */
  public void print(String commandName, int num) {
    // Command Specific errors
    System.out.println(commandName + ": " + errors.get(num));
  }
}
