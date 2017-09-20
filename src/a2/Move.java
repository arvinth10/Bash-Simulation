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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Move class responsible for moving items in the file system, given two paths
 * either relative or absolute
 * 
 * @author c5vijayb
 *
 */
public class Move extends ChangeFileSystem {
  /**
   * Default constructor
   */
  public Move() {

  }

  /**
   * Execute method that calls method in parent class to copy given item, and
   * then removes the item that was copied
   * 
   * @param allArgs[]: String that contains all arguments given be the user
   * @return: none
   */
  public void execute(String allArgs[]) {
    Boolean check = helpExecute(allArgs);
    if (check) { // If the file was moved correctly

      String movingItemPath = pathChanger(allArgs[0]);
      movingItemPath = toAbsolutePath(movingItemPath);
      Hashtable<String, File> fileObjects = fileSystem.getFiles();
      ArrayList<String> remove = new ArrayList<String>();
      Set<String> keyset = fileObjects.keySet();

      for (String pathKey : keyset) { // Removes the original file
        if (pathKey.startsWith(movingItemPath)) {
          remove.add(pathKey);
        }
      }

      for (String path : remove) {
        fileObjects.remove(path);
      }

    }

  }
}
