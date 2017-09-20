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
 * Executes the push command, where the current working directory is pushed on
 * to the stack and then the current working directory is changed to the path
 * given. Error is printed if the path given is not found valid by the validator
 * 
 * @author c5vijayb
 *
 */

public class Pushd extends Command {
  /**
   * Push the current working directory onto the stack then make the current
   * working directory to the one given
   * 
   * @param String allArgs[] Contains all arguments from the command pushd
   * @return None
   * 
   */
  public Pushd() {
    super();
  }

  public void execute(String allArgs[]) {
    Stderr stderr = new Stderr();
    String path = pathChanger(allArgs[0]);
    String refinedPath = toAbsolutePath(path);
    if (Validator.checkPathValidity(refinedPath)
        && Validator.checkPathExists(refinedPath)) {
      handler.pushPath(); // Call setAndPushPath method if path is valid
      Cd change = new Cd();
      change.execute(allArgs);
    } else
      stderr.print("pushd", 3); // Or print error message

  }

}
