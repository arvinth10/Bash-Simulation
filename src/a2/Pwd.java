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
 * Class is responsible for giving the current working directory
 * 
 * @author Mathusan Selvarajah
 *
 */
public class Pwd extends Command {


  /**
   * Constructs a Pwd command object
   */
  public Pwd() {
    super();
  }

  /**
   * Method that gets and prints the current working directory
   * 
   * @param allArgs[]: An string array that stores all arguments passed by user
   *        along with the pwd command.
   */
  public void execute(String[] allArgs) {
    Stdout stdout = new Stdout();
    if (allArgs.length == 2) {
      stdout.print(handler.getCurrentPath(), allArgs[0], allArgs[1]);
    } else {
      stdout.print(handler.getCurrentPath());
    }
  }

}
