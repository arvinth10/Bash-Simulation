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
 * Validates all commands and arguments entered by user and check if it is in
 * correct and valid format. Also check if the paths entered by the users as
 * arguments are valid.
 * 
 * @author
 *
 */
public class Validator {

  /**
   * An array list contain all valid commands in the program
   */
  private ArrayList<String> validCommands = new ArrayList<String>();

  /**
   * Default Constructor seting all valid commands
   */

  private static FileSystem fileSystem = FileSystem.createFileSystem();;

  public Validator() {


    validCommands.add("cat");
    validCommands.add("cd");
    validCommands.add("echo");
    validCommands.add("history");
    validCommands.add("ls");
    validCommands.add("mkdir");
    validCommands.add("popd");
    validCommands.add("pushd");
    validCommands.add("pwd");
    validCommands.add("man");
    validCommands.add("mv");
    validCommands.add("cp");
    validCommands.add("get");
    validCommands.add("grep");
    validCommands.add("!");
  }


  /**
   * Check if the commands enter are valid with the number and type of arguments
   * 
   * @param allArgs The arguments entered by user
   * @return True or False based on whether input is valid
   */
  public boolean checkCMD(String allArgs[]) {
    Stderr stderr = new Stderr();

    if (allArgs.length >= 4) {
      // Check if the entered line has four words and check if the user has
      // entered the echo command and take action accordingly
      if (allArgs.length == 4 && allArgs[0].equals("echo")) {
        if (allArgs[1] == null || allArgs[2] == null || allArgs[3] == null) {
          return false;
        } else {
          if (allArgs[2].equals(">>") || allArgs[2].equals(">")) {
            if (allArgs[1].startsWith("\"") && allArgs[1].endsWith("\"")) {
              return true;
            } else {
              stderr.print(1);
              return false;
            }
          } else {
            stderr.print(1);
            return false;
          }
        }
      } else if (allArgs[0].equals("mkdir") || allArgs[0].equals("cat")
          || allArgs[0].equals("ls")) {
        return true;
      } else if (allArgs[0].equals("grep")) {
        if ((allArgs[1].equalsIgnoreCase("-R") && allArgs[2].startsWith("\"")
            && allArgs[2].endsWith("\""))
            || allArgs[1].startsWith("\"") && allArgs[1].endsWith("\"")) {
          return true;
        } else {
          stderr.print(1);
          return false;
        }
      } else if (allArgs[0].equals("history") || allArgs[0].equals("man")) {
        // When user wants to redirect output of history or man command
        if (allArgs.length == 4) {
          if (allArgs[2].equals(">") || allArgs[2].equals(">>")) {
            if (allArgs[0].equals("history")) {
              if (!allArgs[1].matches("\\d+")) {
                stderr.print(8);
                return false;
              } else {
                return true;
              }
            } else if (allArgs[0].equals("man")) {
              if (!validCommands.contains(allArgs[1])) {
                return false;
              } else {
                return true;
              }
            }
          }
        } else {
          System.out.println("enters");
          stderr.print(1);
          return false;
        }
      } else if (allArgs[0].equals("cat") || allArgs[0].equals("ls")) {
        if (allArgs[allArgs.length - 2].equals(">")
            || allArgs[allArgs.length - 2].equals(">>")) {
          return true;
        } else {
          stderr.print(1);
        }
      } else {
        stderr.print(1);
        return false;
      }

    } else if ((allArgs.length == 3)
        && (allArgs[0].equals("mkdir") || allArgs[0].equals("cat")
            || allArgs[0].equals("ls") || allArgs[0].equals("mv")
            || allArgs[0].equals("cp") || allArgs[0].equals("pwd")
            || allArgs[0].equals("history") || allArgs[0].equals("grep"))) {

      if (allArgs[0].equals("pwd")) {
        if (allArgs[1].equals(">") || allArgs[1].equals(">>")) {
          return true;
        } else {
          stderr.print(1);
          return false;
        }
      } else if (allArgs[0].equals("history")) {
        if (allArgs[1].equals(">") || allArgs[1].equals(">>")) {
          return true;
        } else {
          stderr.print(1);
          return false;
        }
      }

      return true;
    } else if (allArgs.length == 2) {
      if (allArgs[0].equals("history")) {
        if (!allArgs[1].matches("\\d+")) { // Argument passed to history not
                                           // an integer
          stderr.print(8);
          return false;
        } else {
          return true;
        }
      } else if (allArgs[0].equals("echo")) {
        if (allArgs[1].startsWith("\"") && allArgs[1].endsWith("\"")) {
          return true;
        } else {
          stderr.print(9);
          return false;
        }
      } else if (allArgs[0].equals("man") || allArgs[0].equals("pushd")
          || allArgs[0].equals("ls") || allArgs[0].equals("mkdir")
          || allArgs[0].equals("cat") || allArgs[0].equals("cd")
          || allArgs[0].equals("get")) {

        if (allArgs[0].equals("man") && validCommands.contains(allArgs[1])) {
          return true;
        } else if (allArgs[0].equals("man")
            && !validCommands.contains(allArgs[1])) {
          stderr.print(4);
          return false;
        }


        if (allArgs[0].equals("mkdir") || allArgs[0].equals("pushd")
            || allArgs[0].equals("cat") || allArgs[0].equals("cd")
            || allArgs[0].equals("ls") || allArgs[0].equals("get")) {

          if ((allArgs[0].equals("cd") || allArgs.equals("pushd"))
              && allArgs[1].equals("..")) {
            return true;
          }

        } else {
          stderr.print(3);
          return false;
        }

        return true;
      } else {
        stderr.print(1);
        return false;
      }

    } else if (allArgs.length == 1) {
      // Check which one word command has been entered by the user and
      // take action accordingly
      if (allArgs[0].equals("ls") || allArgs[0].equals("pwd")
          || allArgs[0].equals("history") || allArgs[0].equals("exit")
          || allArgs[0].equals("popd") || (allArgs[0].charAt(0) == '!')) {
        if (allArgs[0].charAt(0) == '!') {
          if (allArgs[0].substring(1, allArgs[0].length())
              .matches("^[1-9]\\d*$")) {
            return true;
          } else {
            return false;

          }
        }
        return true;
      } else {
        stderr.print(1);
        return false;
      }

    }
    stderr.print(1);
    return false; // If nothing is entered, will check in jshell

  }

  /**
   * Check if the path given in correct formatting
   * 
   * @param String path: The path to be validated
   * @return True or False based on whether path is in correct format
   */
  protected static boolean checkPathValidity(String path) {

    if (!(path.startsWith("/")) && !(path.startsWith("."))) {
      return true;
    }

    if (!path.contains("//")) {
      if (path.contains(".")) {
        if (path.startsWith("../")) {
          return true;
        } else if (path.startsWith("..") && (path.length() > 2)) {
          return false;
        } else {
          return true;
        }
      } else if (path.startsWith("/")) {
        return true;
      }
    }

    return false; // If path contains two front slashes
  }

  /**
   * This method will just use method in FileSystem, but first create absolute
   * path if, user does not input absolute path. To check if the path exists in
   * the program
   * 
   * @param String path: the path to be validated
   * @return FileSystem.checkIfPathExist(path) : True or False based whether the
   *         path exists
   */
  protected static boolean checkPathExists(String path) {
    Hashtable<String, File> allFiles = fileSystem.getFiles();

    if (allFiles.containsKey(path))
      return true;
    else
      return false;
  }


}

