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
 * This class is responsible for retrieving the contents of either a list of
 * files or even just one file and using an stdout instance to print them. Class
 * should also make sure that the arguments given by the user are correct by
 * using methods from the Validator class to check them.
 * 
 * @author Mathusan Selvarajah
 */


public class Cat extends Command {
  /**
   * Constructs a Cat Instance
   */
  public Cat() {

  }

  /**
   * Executes the Cat command. Does this by taking path arguments (list or even
   * one argument) given by the user and uses a helper method to get contents of
   * a file, then prints the contents of the file with that path.
   * 
   * @param String allArgs[] Contains all arguments of the command (cat)
   * @return None
   */
  public void execute(String allArgs[]) {
    Stdout stdout = new Stdout();
    String redirectionType = "";
    String outFileName = "";
    Boolean redirect = false;
    if (allArgs.length >= 3) {
      if ((allArgs[allArgs.length - 2].equals(">")
          || allArgs[allArgs.length - 2].equals(">>"))) {

        outFileName = allArgs[allArgs.length - 1];
        redirectionType = allArgs[allArgs.length - 2];
        String updatedArgs[] =
            Arrays.copyOfRange(allArgs, 0, allArgs.length - 2);
        allArgs = updatedArgs;
        redirect = true;
      }
    }
    String output = helpExecute(allArgs);
    if (!(output == null)) {
      if (redirect) {
        Echo echoCmd = new Echo();
        String echoArgs[] = {output, redirectionType, outFileName};
        echoCmd.execute(echoArgs);
      } else
        stdout.print(output);
    }
  }

  /**
   * Helps Executes the Cat command. Does this by taking path arguments (list or
   * even one argument) given by the user and returns the contents of the file
   * with that path. Error checking is done using methods from Validator class.
   * 
   * @param String allArgs[] Contains all arguments of the command (cat)
   * @return String: The contents of a file
   */
  public String helpExecute(String allArgs[]) {
    // Assume validator has already checked that Cat arguments are
    // greater than or equal to 1
    Stderr stderr = new Stderr();
    String output = "";
    for (int i = 0; i < allArgs.length; i++) {
      Hashtable<String, File> allFiles = fileSystem.getFiles();
      String path = pathChanger(allArgs[i]);
      String refinedPath = toAbsolutePath(path);

      if (Validator.checkPathExists(refinedPath)
          && Validator.checkPathValidity(refinedPath)) {
        // Using methods from validator class to check if path is
        // Valid and exists.

        // String nameAndPath[] = FileSystem.newObject(path);

        if (allArgs[i].equals(".")) {
          // Print error
          stderr.print(3);
        } else {
          File file = allFiles.get(refinedPath);
          if (file instanceof Directory) {
            // If the given path contained a Directory
            // Instead of a file...
            String dirName = file.getName();
            stderr.print(dirName, 5);
            // Print error
          } else {
            output = output + ((((TextFile) file).getContents()));
            output = output + "\n\n\n\n";
            // Followed by three blank lines
          }
        }
      } else {
        // Print error
        stderr.print("cat", 3);
      }
    }
    if (output.length() != 0) {
      return output;
    }
    return null;
  }

}


