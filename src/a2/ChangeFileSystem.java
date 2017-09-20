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
import java.util.Set;

/**
 * Parent class of move and copy that contains methods common to both.
 * Responsible for moving/copying given files
 * 
 * @author c5vijayb
 *
 */
public abstract class ChangeFileSystem extends Command {
  /**
   * Default Constructor
   */
  public ChangeFileSystem() {
    super();
  }

  /**
   * Determines what the type of object being moves/copies is and what the type
   * of the object that is is being moved/copied too. Then calls appropriate
   * methods
   * 
   * @param allArgs: A string array that contains all arguments given by the
   *        user
   * @return: None
   */
  public boolean helpExecute(String allArgs[]) {
    Echo echo = new Echo();
    Mkdir mkdir = new Mkdir();
    Stderr stderr = new Stderr();
    Hashtable<String, File> fileObjects = fileSystem.getFiles();

    // Changing all given paths to absolute paths
    String movingItemPath = pathChanger(allArgs[0]);
    String movingToPath = pathChanger(allArgs[1]);
    movingItemPath = toAbsolutePath(movingItemPath);
    movingToPath = toAbsolutePath(movingToPath);
    String nameAndPath[];


    // Cases where moving or copying item while changing it name
    if (!Validator.checkPathExists(movingToPath)
        && Validator.checkPathExists(Cd.toParentDirectory(movingToPath))) {
      if (Validator.checkPathExists(movingItemPath)) {
        File movingItem = fileObjects.get(movingItemPath);
        nameAndPath = fileSystem.newObject(movingToPath);
        if (movingItem instanceof TextFile) {

          echo.newTextFile(null, nameAndPath); // Adding a text file under new
                                               // given name
        } else {
          String arg[] = {movingToPath};
          mkdir.execute(arg);// Adding directory object under new name

        }
      }
    }
    String checker; // Used to check for multiples
    int lastSlash = movingItemPath.lastIndexOf("/");
    if (lastSlash == 0)
      checker = "/";
    else
      checker = movingItemPath.substring(0, lastSlash);
    // Validates if paths are valid first or call error messages
    if (Validator.checkPathExists(movingItemPath)
        && Validator.checkPathExists(movingToPath)
        && !movingItemPath.equals(movingToPath)
        && !checker.equals(movingToPath)) {


      File movingItem = fileObjects.get(movingItemPath);
      File movingTo = fileObjects.get(movingToPath);

      if (movingItem instanceof TextFile) {// if moving/copying text file
        return changeTextFile(movingItem, movingTo, movingToPath);


      } else { // if moving/copying directory
        return changeDirectory(movingItem, movingTo, movingToPath,
            movingItemPath);// Directory object
      }

    } else {// Prints errors
      stderr.print(3);
      if (!Validator.checkPathExists(allArgs[0]))
        stderr.print(allArgs[0], 3);
      if (!Validator.checkPathExists(allArgs[1]))
        stderr.print(allArgs[1], 3);

      return false;
    }
  }

  /**
   * Method used to move/copy a Directory object in the file system
   * 
   * @param movingItem: Directory
   * @param movingTo: Directory
   * @param movingToPath: String
   * @param movingItemPath: String
   * @return: true
   */
  private boolean changeDirectory(File movingItem, File movingTo,
      String movingToPath, String movingItemPath) {
    Stderr stderr = new Stderr();
    String newPath;
    Hashtable<String, File> fileObjects = fileSystem.getFiles();

    if (movingTo instanceof TextFile) {
      stderr.print(10);
      return false;
    } else {
      if (movingToPath.startsWith(movingItemPath)) {
        stderr.print(13);
        return false;
      } else {
        if (movingToPath.equals("/")) // Puts newPath of directory
          newPath = "/" + movingItem.getName();
        else
          newPath = movingToPath + "/" + movingItem.getName();
        // calling methods to overwrite a existing directory or create a new one
        if (fileObjects.containsKey(newPath)) { // Overwriting the directory
          overwriteDirectory(movingItemPath, newPath);


        } else { // putting directory in a directory that already exists

          addingToDirectory(movingItemPath, newPath);

        }

        return true;

      }
    }
  }

  /**
   * Creates a new directory to move/copy directory to
   * 
   * @param movingItemPath: String
   * @param newPath: String of the new path for the directory being moved/copied
   */
  private void addingToDirectory(String movingItemPath, String newPath) {

    Hashtable<String, File> fileObjects = fileSystem.getFiles();
    Set<String> keyset = fileObjects.keySet();
    int pathEnd = movingItemPath.length();
    Hashtable<String, File> add = new Hashtable<String, File>();

    for (String pathKey : keyset) { // For all paths if path starts with moving
                                    // item path
      if (pathKey.startsWith(movingItemPath)) {
        // Creates new directory to add to the file system
        File tempFile =
            new Directory(fileObjects.get(pathKey).getName(), pathKey);

        String newKey = newPath + "/" + pathKey.substring(pathEnd);
        newKey = pathChanger(newKey);
        tempFile.changePath(newKey);
        add.put(newKey, tempFile);
      }

    }
    // all new directories are added to the file system
    Set<String> adder = add.keySet();
    for (String path : adder) {
      fileSystem.addFile(path, add.get(path));
    }

  }

  /**
   * Overwrites an existing directory with contents of the moving/copying item
   * 
   * @param movingItemPath: String
   * @param newPath: String of the new path for the directory being moved/copied
   */
  private void overwriteDirectory(String movingItemPath, String newPath) {
    Hashtable<String, File> fileObjects = fileSystem.getFiles();
    Set<String> keyset = fileObjects.keySet();
    int pathEnd = movingItemPath.length();
    Hashtable<String, File> add = new Hashtable<String, File>();

    Hashtable<String, File> remove = new Hashtable<String, File>();

    for (String pathKey : keyset) {
      if (pathKey.startsWith(movingItemPath)) { // For all paths starting with
                                                // moving item

        File tempFile =
            new Directory(fileObjects.get(pathKey).getName(), pathKey);
        String newKey = newPath + "/" + pathKey.substring(pathEnd);
        newKey = pathChanger(newKey); // Creates new directories to be added
        tempFile.changePath(newKey);
        add.put(newKey, tempFile);
      }

    }
    Set<String> adder = add.keySet();
    Set<String> remover = remove.keySet();
    // Adds all new directories
    for (String path : adder) {

      fileSystem.addFile(path, add.get(path));
    }
    // removes directories that were overwritten
    for (String path : remover) {
      fileSystem.removeFile(path);
    }
  }

  /**
   * Method used to move/copy text files in the file system
   * 
   * @param movingItem: TextFile
   * @param movingTo: TextFile
   * @param movingToPath: String
   * @return: true
   */
  private boolean changeTextFile(File movingItem, File movingTo,
      String movingToPath) {
    Echo move = new Echo();
    String newPath;
    Hashtable<String, File> fileObjects = fileSystem.getFiles();

    if (movingTo instanceof TextFile) {
      String contents = ((TextFile) movingItem).getContents();
      move.overWriteFile(contents, movingToPath); // Overwrites if
                                                  // moving/copying to a text
                                                  // file
    } else { // if moving/copying to a directory
      if (movingToPath.equals("/")) // Adds to the directory given
        newPath = "/" + movingItem.getName();
      else
        newPath = movingToPath + "/" + movingItem.getName();
      movingItem.changePath(newPath);

      if (fileObjects.containsKey(newPath)) {
        fileObjects.put(newPath, movingItem);
      } else {

        fileSystem.addFile(newPath, movingItem);
      }

    }
    return true;
  }

}
