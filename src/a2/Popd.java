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
 * Executes the popd command by changing the current working directory to the
 * last one pushed onto the stack. If there are no items on the stack, error
 * message will be printed using the stderr class.
 * 
 * @author c5vijayb
 *
 */

public class Popd extends Command {


  /**
   * Changes the current working directory to the last one that was pushed to
   * the stack, calls error message if there is no directory pushed.
   * 
   * @param String allArgs[] contains arguments from command popd
   * @return None
   */
  public Popd() {
    super();
  }

  public void execute(String allArgs[]) {
    Stderr stderr = new Stderr();
    if (handler.getTracker().empty())
      stderr.print("popd", 6); // If no item in stack, call error
    else
      handler.popPath(); // Or call the popPath method
  }
}
