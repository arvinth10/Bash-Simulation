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
 * Executes echo command by implementing methods to check OUTFILE validity,
 * append/overwrite files, and printing to shell.
 * 
 * @author c5vijayb
 *
 */

public class Echo extends Command {

  /**
   * Default Constructor
   */
  public Echo() {

  }

  /**
   * Executes the echo command. Call appropriate method based on the input
   * arguments. Uses validator to check if the paths given are valid and prints
   * error messages if not
   *
   * @param allArgs[] Contains all arguments of the command (Cd)
   * @return None
   */

  public void execute(String allArgs[]) {
    Stderr stderr = new Stderr();


    allArgs[0] = allArgs[0].replaceAll("\"", ""); // Gets rid of quotes

    if (allArgs.length == 1) {
      printToShell(allArgs[0]); // Prints to console
    } else {
      String path = pathChanger(allArgs[2]);
      path = toAbsolutePath(path);
      String nameAndPaths[] = fileSystem.newObject(path);
      // Gets name and paths of the outfile and directory it may be in
      if (nameAndPaths[0].equals("Error")) {
        stderr.print("echo", 3);
      } else {
        if (Validator.checkPathExists(nameAndPaths[1])) {
          Hashtable<String, File> allFiles = fileSystem.getFiles();
          if (allFiles.get(path) instanceof TextFile) {
            if (allArgs[1].equals(">")) {
              appendFile(allArgs[0], nameAndPaths[1]);
            } else if (allArgs[1].equals(">>")) {
              overWriteFile(allArgs[0], nameAndPaths[1]);
            }
          } else
            System.out.println("Path is a directory exception");
        } else if (Validator.checkPathValidity(nameAndPaths[1])) {
          // Creates new TextFile when none is found
          newTextFile(allArgs[0], nameAndPaths);
        } else {
          stderr.print("echo", 3);
        }
      }



    }
  }

  public void newTextFile(String input, String nameAndPaths[]) {
    TextFile echo = new TextFile(nameAndPaths[0], nameAndPaths[1]);
    echo.setContent(input);
    fileSystem.addFile(nameAndPaths[1], echo);
  }

  /**
   * Given a path and an input, it overwrite the contents of the TextFile
   * located at the path.
   * 
   * @param input The string inputed without quotation marks
   * @param path Path of the outfile to overwrite
   */

  public void overWriteFile(String input, String path) {

    Hashtable<String, File> fileObjects = fileSystem.getFiles();
    // Get hashtable of all fileObjects
    ((TextFile) fileObjects.get(path)).setContent(input);
    // Add to the hashtable and set the hashtable in FileSystem
    fileSystem.setFiles(fileObjects);


  }

  /**
   * Given a path and an input, its appends the contents of the TextFile located
   * at the given path.
   * 
   * @param input The string inputed without quotation marks
   * @param path Path of the outfile to append
   */

  private void appendFile(String input, String path) {
    Hashtable<String, File> fileObjects = fileSystem.getFiles();
    // Get hashtable of all fileObjects
    ((TextFile) fileObjects.get(path)).appendToFile(input);
    // Add TextFile to object and set the hashtable in FileSystem
    fileSystem.setFiles(fileObjects);

  }

  /**
   * Print the given input to the console using stout method
   * 
   * @param input The string to be output to console
   */

  private static void printToShell(String input) {
    Stdout stdout = new Stdout();
    stdout.print(input);
  }


}
