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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author c5vijayb
 * 
 *         Grep class is responsible for printing lines containing REGEX in a
 *         given path
 *
 */
public class Grep extends Command {
  /**
   * Default constructor
   */
  public Grep() {

  }

  private Boolean redirect;
  private String outfile;
  private String redirectType;
  private String output;

  /**
   * An execute method that call the correct method based on if a -R argument is
   * given
   * 
   * @param allArg[]: A String array that contains all all arguments given by
   *        the user
   * 
   * @return: none
   */

  public void execute(String allArgs[]) {
    redirect = false;
    outfile = "";
    redirectType = "";
    output = "";
    if (allArgs[0].equalsIgnoreCase("-R")) {
      allArgs = Arrays.copyOfRange(allArgs, 1, allArgs.length);// Rest of array
                                                               // without -R
                                                               // argument
      recursivelyExecute(allArgs);

    } else {
      regularExecute(allArgs);
    }

    if (redirect) { // If user wants to redirect output to a file..
      Echo echoCmd = new Echo(); // Use Echo command to enable user to do this
      String echoArgs[] = {output, redirectType, outfile};
      echoCmd.execute(echoArgs);
      redirect = false;
      output = "";

    }
  }

  /**
   * Method used to recursively find all textFile in a given directory and
   * search them line by line to find regex
   * 
   * @param allArgs[]: A String array that contains all arguments given by the
   *        user except for -R
   * 
   * @return: none
   */
  private void recursivelyExecute(String allArgs[]) {
    String regex = allArgs[0];
    Hashtable<String, File> allFiles = fileSystem.getFiles();
    Stderr stderr = new Stderr(); 
    if (allArgs[allArgs.length - 2].equals(">")
        || allArgs[allArgs.length - 2].equals(">>")) {
      redirectType = allArgs[allArgs.length - 2];
      outfile = allArgs[allArgs.length - 1];
      redirect = true;
      allArgs = Arrays.copyOfRange(allArgs, 0, allArgs.length - 2);
    }

    for (int i = 1; i < allArgs.length; i++) {

      String path = pathChanger(allArgs[i]);
      String absPath = toAbsolutePath(path);
      int index =path.length();
      if (Validator.checkPathExists(absPath)) {
        File file = allFiles.get(absPath);

        if (file instanceof TextFile) {
          printGrep(regex, ((TextFile) file).getContents(), path);

        } else { // file is directory

          // Goes through the hashtable which contains all paths and
          // finds paths that start with the directory path given
          Set<String> keyset = allFiles.keySet();

          ArrayList<String> allPathsContained = new ArrayList<String>();
          ArrayList<String> sortedKeys = new ArrayList<String>();

          // Getting all paths with the given path
          for (String pathKey : keyset) {
            if (pathKey.contains(absPath)
                && allFiles.get(pathKey) instanceof TextFile) {
              allPathsContained.add(pathKey);
            }
          }
          // Sorting the paths retrieved
          while (allPathsContained.size() != 0) {
            String temp = allPathsContained.get(0);

            for (int j = 0; j < allPathsContained.size(); j++) {
              if (allPathsContained.get(j).compareTo(temp) < 0) {
                temp = allPathsContained.get(j);
              }
            }

            sortedKeys.add(temp);
            allPathsContained.remove(temp);
          }
          String toFilePath;
          // calling printGrep for all textFiles found
          for (String textPath : sortedKeys) {
            File rFile = allFiles.get(textPath);
            if (path.equals("/"))
              toFilePath = textPath.substring(index);
            else
              toFilePath = textPath.substring(index +1); 
            printGrep(regex, ((TextFile) rFile).getContents(), toFilePath);
          }

        }

      } else {
        stderr.print(3);
      }
    }
  }

  /**
   * Finds the lines that contain regex and prints it
   * 
   * @param regex: String that is being searched for
   * @param contents: String of contents of the text file
   * @return: none
   */

  private void printGrep(String regex, String contents) {
    Stdout stdout = new Stdout();
    Scanner in = new Scanner(contents);
    String line;
    regex = regex.replaceAll("\"", ""); // removes quotations
    while (in.hasNextLine()) {
      line = in.nextLine();

      if (line.contains(regex)) {
        if (redirect) {
          output = output + line + "\n";
        } else {
          stdout.print(line); // Prints all lines containing regex
        }
      }
    }
    in.close();

  }

  /**
   * Finds the lines that contain regex and prints it along with the path to the
   * text file
   * 
   * @param regex: String that is being searched for
   * @param contents: String of contents of the text file
   * @return: none
   */
  private void printGrep(String regex, String contents, String path) {
    Stdout stdout = new Stdout();
    regex = regex.replaceAll("\"", "");
    Scanner in = new Scanner(contents);
    String line;
    while (in.hasNextLine()) {
      line = in.nextLine();
      if (line.contains(regex)) {// Prints all lines containing regex along
        if (redirect) { // with the path
          output = output + path + ": " + line + "\n";
        } else {
          stdout.print(path + ": " + line);
        }
      }
    }
    in.close();
  }

  /**
   * 
   * Finds all lines in a given text file that contains the given regex
   * 
   * @param allArgs[]: A String array that contains all arguments given by the
   *        user
   * 
   * @return: none
   */
  private void regularExecute(String allArgs[]) {
    String regex = allArgs[0];
    Hashtable<String, File> allFiles = fileSystem.getFiles();
    Stderr stderr = new Stderr();

    if (allArgs[allArgs.length - 2].equals(">") // If user wants to redirect
        || allArgs[allArgs.length - 2].equals(">>")) { // The output
      redirectType = allArgs[allArgs.length - 2];
      outfile = allArgs[allArgs.length - 1];
      redirect = true;
      allArgs = Arrays.copyOfRange(allArgs, 0, allArgs.length - 2);
    }

    for (int i = 1; i < allArgs.length; i++) {

      String path = pathChanger(allArgs[i]);
      path = toAbsolutePath(path);

      if (Validator.checkPathExists(path)) {
        File file = allFiles.get(path);

        if (file instanceof TextFile) {// Finds regex only if TextFile or return
                                       // appropriate error message
          printGrep(regex, ((TextFile) file).getContents());
        } else {
          stderr.print(5);
        }

      } else {
        stderr.print(3); 
      }
    }
  }

}
