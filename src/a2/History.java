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

public class History extends Command {

  private ArrayList<String> recentCommands;
  // ArrayList used to store all recent commands
  private static int numOfTimes = 0;
  private static History singleHistory = null;

  /**
   * Constructs a History command object, and initializes an array list to store
   * recent commands in. Can only be called once during the runtime.
   * 
   * Author: Mathusan Selvarajah, Rixin Dai
   */
  private History() {
    recentCommands = new ArrayList<String>();
  }

  /**
   * Method creates a new History object if and only if there is none been
   * created
   * 
   * @return: A history object
   */
  public static History createHistory() {
    if (singleHistory == null) {
      singleHistory = new History();
    }
    return singleHistory;
  }

  /**
   * Saves the line entered by user via keyboard input to an array list increase
   * the number of input times
   * 
   * @param line: A string variable that keeps the line entered by the user
   * @return: none
   */
  public void addToHistory(String line) {
    recentCommands.add(line);
    numOfTimes++;
  }

  /**
   * Method that returns the number of total lines input
   * 
   * @return toReturn: the number of total lines that the user had input
   */
  protected static int getNumber() {
    int toReturn = History.numOfTimes;
    return toReturn;
  }

  /**
   * Method returns a list which contains the history of all user input.
   * 
   * @return recentCommands: A String list that returns a list of all recent
   *         commands.
   */
  public ArrayList<String> getHistory() {
    return recentCommands;
  }

  /**
   * A overloaded method that returns the user input at the time of index
   * 
   * @return toReturn: A String that represent the input required
   */
  public String getHistory(int index) {
    String toReturn = recentCommands.get(index);
    return toReturn;
  }

  /**
   * A method that executes the History command, by printing out all commands
   * entered by the user
   * 
   * @param allArgs: A string array that stores the arguments that user entered
   *        after entering the command "history"
   * @return none
   */
  private Boolean redirect;

  public void execute(String allArgs[]) {
    Stdout stdout = new Stdout();
    ArrayList<String> commands = new ArrayList<String>();
    // Commands = helpExecute(allArgs);
    commands = helpExecute(allArgs);

    if (allArgs.length == 3) {
      if (allArgs[1].equals(">") || allArgs[1].equals(">>")) {
        redirect = true;
      }
    } else if (allArgs.length == 2) {
      if (allArgs[0].equals(">") || allArgs[0].equals(">>")) {
        redirect = true;
      } else {
        redirect = false;
      }
    } else {
      redirect = false;
    }

    if (commands != null) {
      if (redirect & allArgs.length == 2) {
        stdout.print(commands, allArgs[0], allArgs[1]);
      } else if (redirect & allArgs.length == 3) {
        stdout.print(commands, Integer.parseInt(allArgs[0]), allArgs[1],
            allArgs[2]);
      } else {
        if (allArgs.length == 0) {
          stdout.print(commands);
        } else {
          stdout.print(commands, Integer.parseInt(allArgs[0]));
        }
      }
    }
  }

  /**
   * A method that helps execute method by returning all commands entered by the
   * user, or all commands after a certain number of commands.
   * 
   * @param allArgs: A string array that stores the arguments that user entered
   *        after entering the command "history"
   * @return none
   */
  public ArrayList<String> helpExecute(String allArgs[]) {
    ArrayList<String> commands = new ArrayList<String>();

    if (allArgs.length == 0 || allArgs.length == 2) {
      // If there are no arguments to History command
      commands = this.getHistory();
      return commands;
    } else {
      // ArrayList<String> CommandsAfterNum = new ArrayList<String>();
      int num = Integer.parseInt(allArgs[0]);

      if (num > recentCommands.size()) {
        Stderr stderr = new Stderr();

        stderr.print("history", 8);
        return null;
      }

      for (int i = 0; i < recentCommands.size(); i++) {
        if (i >= num) {
          commands.add(recentCommands.get(i));
        }
      }

      return commands;
    }
  }
}
