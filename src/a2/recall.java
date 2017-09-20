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

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Recall class is used to execute the !number command. Where user can recall
 * any previous command they had entered
 * 
 * @author c5selvar
 *
 */
public class recall extends Command {

  /**
   * Execute method uses the history command to try and execute a previous
   * command entered by the user.
   */
  public void execute(String allArgs[]) {

    // Creating all the commands

    Hashtable<String, Command> allCommands = new Hashtable<String, Command>();

    // Adding all command instances to a hashtable
    allCommands.put("cat", new Cat());
    allCommands.put("cd", new Cd());
    allCommands.put("echo", new Echo());
    allCommands.put("history", History.createHistory());
    allCommands.put("ls", new Ls());
    allCommands.put("mkdir", new Mkdir());
    allCommands.put("popd", new Popd());
    allCommands.put("pushd", new Pushd());
    allCommands.put("pwd", new Pwd());
    allCommands.put("man", new Man()); // All possible commands

    History history = History.createHistory();
    String numberAsString = allArgs[0].charAt(1) + "";
    Stderr stderr = new Stderr();

    int number = Integer.parseInt(numberAsString);
    if (number < history.getHistory().size()) {
      String cmd = history.getHistory(number - 1);
      String args[] = cmd.split(" "); // Split by white space

      // Check if the cmd is valid first
      Validator validator = new Validator();
      if (!((args.length == 1) && (args[0].equals("")))) {
        if (validator.checkCMD(args) == true) {

          String refinedArgs[] = Arrays.copyOfRange(args, 1, args.length);
          allCommands.get(args[0]).execute(refinedArgs);

        } else {
          stderr.print(1);
        }
      } else {
        stderr.print(1);
      }

    } else {
      stderr.print(11);
    }
  }
}
