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

/**
 * 
 * Changes the current working directory based on a give relative or basic path
 * And can return the parent directory of a path
 * 
 * @author c5vijayb
 *
 */
public class Cd extends Command {
  /**
   * Default constructor
   */

  public Cd() {
    super();
  }

  /**
   * Executes the Cd command. Takes the arguments array which should only c
   * contain one element in this case, the path. Then check if this path is
   * valid as either relative or absolute using checkifPathExists(). Then also
   * check if the path points to a directory and not TextFile before changing
   * the current working directory.
   *
   * @param allArgs[] Contains all arguments of the command (Cd)
   * @return None
   */
  public void execute(String allArgs[]) {

    Stderr stderr = new Stderr();
    String path = allArgs[0];
    String newPath = "";
    path = pathChanger(path);
    path = toAbsolutePath(path);

    if (path.equals(".."))
      // If argument is only ".." then current working directory goes to parent
      // directory
      handler.setCurrentPath(toParentDirectory(handler.getCurrentPath()));

    else if (!path.equals(".")) {
      // When path only equal "." nothing happens
      path = pathChanger(path);
      // pathChanger called to make path easier to work with

      // then three checks made to see if the give path exists as either
      // relative or absolute
      if (Validator.checkPathExists(path))
        newPath = path;
      else if (Validator.checkPathExists(handler.getCurrentPath() + path)) {
        newPath = handler.getCurrentPath() + path;
      } else if (Validator
          .checkPathExists(handler.getCurrentPath() + "/" + path)) {
        newPath = handler.getCurrentPath() + "/" + path;
      } else {
        stderr.print("cd", 3);
        // If no path that exists is found, print error message
      }

      if (!newPath.equals("")
          && !(fileSystem.getFiles().get(newPath) instanceof TextFile)) {
        // If a valid path was found and path is not a TextFile
        handler.setCurrentPath(newPath);
      } else if (fileSystem.getFiles().get(newPath) instanceof TextFile) {
        // Or print error that the path found is a TextFile
        stderr.print(newPath, 7);
      }
    }
  }

  /**
   * Given an absolute path, its returns the parent directory of the path.
   *
   * @param path Absolute path of path to get parent directory
   * @return newPath Absolute path of the parent directory
   */
  protected static String toParentDirectory(String path) {

    if (path.length() - path.replaceAll("/", "").length() == 1)
      // If path is "/" or "/example"
      return "/";
    else {
      // Removes last part of path
      int lastSlash = path.lastIndexOf("/");
      String newPath = path.substring(0, lastSlash);
      return (newPath);
    }
  }
}

