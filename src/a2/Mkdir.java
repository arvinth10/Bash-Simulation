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
 * Mkdir class is responsible for creating a directory given the directory name.
 * This class creates directories given an absolute or relative path. It also
 * validates the given paths by collaborating with the validator class.
 * 
 * @author Mathusan Selvarajah, Rixin Dai
 *
 */
public class Mkdir extends Command {

  /**
   * Constructs an Mkdir command object
   */
  public Mkdir() {

  }

  /**
   * An execute method that creates a directory relative to current directory
   * Path string is either the current path or the path that user enters
   * 
   * @param allArgs[]: A String method that contains all arguments given by user
   *        along with mkdir command
   * @return: none
   */
  public void execute(String allArgs[]) {
    Stderr stderr = new Stderr();

    for (int i = 0; i < allArgs.length; i++) {
      if (allArgs[i].contains("//") || allArgs[i].equals(".")) {
        stderr.print("mkdir", 3);
      } else {

        // Means it is an absolute or a relative path
        if (Validator.checkPathValidity(allArgs[i])) {
          String path = pathChanger(allArgs[i]);
          path = toAbsolutePath(path);
          String nameAndPath[] = fileSystem.newObject(path);


          // Using newObject method to get the name and the absolute path of
          // Of the given path

          if (nameAndPath[0].equals("Error")) {
            stderr.print("mkdir", 3);
          } else {
            if (!Validator.checkPathExists(nameAndPath[1])
                && Validator.checkPathValidity(nameAndPath[1])) {
              fileSystem.addFile(nameAndPath[1],
                  new Directory(nameAndPath[0], nameAndPath[1]));
            } else {
              stderr.print("mkdir", 3);
            }
          }

        } else {
          stderr.print("mkdir", 3);
        }
      }
    }
  }
}
