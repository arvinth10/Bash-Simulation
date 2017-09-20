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
// UT Student #:
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

public class Ls extends Command {
  /**
   * Constructs an Ls command object
   */
  public Ls() {
    super();
  }


  /**
   * A method that executes the Ls command by listing all the directories within
   * a directory with a relative or an absolute path
   *
   * @param allArgs[]: A string array that stores the arguments given by the
   *        user after entering the command "ls"
   * @return: none
   */
  public void execute(String allArgs[]) {
    Boolean redirect = false;
    String output = "";

    if (allArgs.length == 0 || (allArgs.length == 2
        && (allArgs[0].equals(">") || allArgs[0].equals(">>")))) {
      // When there is no arguments passed with ls command
      // So no path or directory given, means ls of current working directory
      if (allArgs.length == 2) {
        if (allArgs[0].equals(">") || allArgs[0].equals(">>")) {
          redirect = true;
        }
      }
      String currentWD = handler.getCurrentPath();
      Hashtable<String, File> allFiles;
      allFiles = fileSystem.getFiles();
      // allFile hashtable contains both file and text objects

      Directory dir = (Directory) (allFiles.get(currentWD));
      ArrayList<String> contents = dir.getContents();
      /*
       * This is the array list of paths of the contents that current directory
       * holds
       */
      Stdout stdout = new Stdout();

      for (String content : contents) {

        String nameOfFile = allFiles.get(content).getName();
        // Here i don't have to check if the file is a text file or directory
        // because getName is a method implemented in super class of Textfile
        // class and Directory class.
        if (redirect) {
          output = output + nameOfFile + "\n";
        } else
          stdout.print(nameOfFile);
      }
    } else if (allArgs[0].equalsIgnoreCase("-r")) {
      if (allArgs.length == 1) { // If -r was given with no path
        // Must execute recursively for current path
        String currentPath = handler.getCurrentPath();
        // ArrayList <String> tempList = new ArrayList <String>();

        String paths[] = new String[1];
        paths[0] = currentPath;
        // Changing up given argument to include
        // current path
        recursivelyExecute(paths);
      } else {
        String refinedArgs[] = Arrays.copyOfRange(allArgs, 1, allArgs.length);
        recursivelyExecute(refinedArgs); ///////////////////////////
      }
    } else {
      // When one or more arguments are passed along with he command ls
      helpExecute2(allArgs);
    }
    if (redirect) {
      String echoArgs[] = {output, allArgs[0], allArgs[1]};
      Echo echoCmd = new Echo();
      echoCmd.execute(echoArgs);
    }
  }

  /**
   * A helper method for the helpExecute2 method, that is used to execute the ls
   * command when the path is just a dot (.)
   * 
   * @return: none
   */
  private void helpExecute1() {
    String currentWD = handler.getCurrentPath();
    Hashtable<String, File> allFiles;
    allFiles = fileSystem.getFiles();

    Directory dir = (Directory) (allFiles.get(currentWD));
    ArrayList<String> contents = dir.getContents();

    Stdout stdout = new Stdout();

    for (String content : contents) {
      String nameOfFile = allFiles.get(content).getName();
      stdout.print(nameOfFile);
    }
  }

  /**
   * A helper method for the execute method, that is used to execute the ls
   * command when the user enters any arguments along with the ls command.
   * 
   * @param allArgs: A String array that stores all arguments passed along with
   *        the ls command
   * @return: none
   */

  private void helpExecute2(String allArgs[]) {
    Stdout stdout = new Stdout();
    Stderr stderr = new Stderr();
    Hashtable<String, File> allFiles;
    allFiles = fileSystem.getFiles();

    Boolean redirect = false;
    String output = "";

    int iterate = 0;
    if (allArgs.length >= 3) {
      if (allArgs[allArgs.length - 2].equals(">")
          || allArgs[allArgs.length - 2].equals(">>")) {
        iterate = allArgs.length - 2;
        redirect = true;
      } else {
        iterate = allArgs.length;
      }
    } else {
      iterate = allArgs.length;
    }

    for (int i = 0; i < iterate; i++) {
      if (allArgs[i].equals(".")) {
        // If the argument was just a dot, use the helpExecute1 helper method.
        helpExecute1();
      } else {
        String path = pathChanger(allArgs[i]);
        String refinedPath = toAbsolutePath(path);

        if (Validator.checkPathExists(refinedPath)
            && Validator.checkPathValidity(refinedPath)) {

          // Convert the given path to an absolute path.

          // String nameAndPath[] = FileSystem.newObject(path);

          if (allFiles.get(refinedPath) instanceof TextFile) {
            if (redirect) {
              output = output + allFiles.get(refinedPath).getName();
            } else {
              stdout.print(allFiles.get(refinedPath).getName());
            }
            // Just print the name of the file
          } else {
            Directory dir = (Directory) (allFiles.get(refinedPath));
            ArrayList<String> contents = dir.getContents();

            if (allArgs.length == 1) { // Just for some cleaner formatting
              for (String content : contents) {
                String nameOfDirectory = allFiles.get(content).getName();
                if (redirect) {
                  output = output + nameOfDirectory + "\n";
                } else {
                  stdout.print(nameOfDirectory);
                }
              }
            } else {
              if (refinedPath.equals("/")) {
                if (redirect) {
                  output = output + "/:" + "\n";
                } else {
                  stdout.print("/:");
                }
              } else {
                if (redirect) {
                  output = output + dir.getName() + ":" + "\n";
                } else {
                  stdout.print(dir.getName() + ":");
                }
              }
              for (String content : contents) {
                String nameOfDirectory = allFiles.get(content).getName();
                if (redirect) {
                  output = output + nameOfDirectory + "\n";
                } else {
                  stdout.print(nameOfDirectory);
                }
              }
            }
          }
        } else {
          stderr.print("ls " + allArgs[i], 3);
        }
      }
    }
    if (redirect) {
      stdout.print(output, allArgs[allArgs.length - 2],
          allArgs[allArgs.length - 1]);
    }
  }

  /**
   * This method is a helper method that will recursively get contents of a
   * given file and contents from its sub-directories
   * 
   */
  private void recursivelyExecute(String allArgs[]) {
    Boolean redirect = false;
    int iterate = 0;

    String output = "";
    if (allArgs.length >= 2) {
      if (allArgs.length == 2
          & (allArgs[0].equals(">") || allArgs[0].equals(">>"))) {
        // When input is ls -r > outfile

        String currentPath = handler.getCurrentPath();
        String updatedArgs[] = {currentPath, allArgs[0], allArgs[1]};
        allArgs = updatedArgs;
        redirect = true;
        iterate = allArgs.length - 2;
      } else {
        if (allArgs[allArgs.length - 2].equals(">")
            || allArgs[allArgs.length - 2].equals(">>")) {

          iterate = allArgs.length - 2;
          redirect = true;
        } else {
          iterate = allArgs.length;
          redirect = false;
        }
      }
    }

    Stdout stdout = new Stdout();
    Hashtable<String, File> allFiles;
    allFiles = fileSystem.getFiles();

    for (int i = 0; i < allArgs.length; i++) {// For each path

      String tempPath = pathChanger(allArgs[i]);
      String refinedPath = toAbsolutePath(tempPath);

      if (Validator.checkPathExists(refinedPath)
          && Validator.checkPathValidity(refinedPath)) {

        if (allFiles.get(refinedPath) instanceof Directory) {

          // ArrayList<String> allPathsContained = new ArrayList<String>();
          // Need to make sure that this is reset every time

          // Goes through the hashtable which contains all the paths and finds
          // paths
          // That contain the given path, so we can get access to
          // All the files that are contained by the folder in a given path and
          // all
          // files of its sub-directories

          Set<String> keyset = allFiles.keySet();

          ///
          ArrayList<String> allPathsContained = new ArrayList<String>();
          ArrayList<String> sortedKeys = new ArrayList<String>();


          for (String pathKey : keyset) {
            if (pathKey.contains(refinedPath)) {
              allPathsContained.add(pathKey);
            }
          }

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

          for (String path : sortedKeys) {
            if (allFiles.get(path) instanceof Directory) {
              Directory dir = (Directory) allFiles.get(path);

              ArrayList<String> contents = dir.getContents();

              // Indicating which directory these contents are from
              if (redirect)
                output = output + dir.getName() + ":" + "\n";
              else
                stdout.print(dir.getName() + ":");

              // Printing all the contents of the directory
              for (String content : contents) {
                if (redirect)
                  output = output + allFiles.get(content).getName() + "\n";
                else
                  stdout.print(allFiles.get(content).getName());
              }

            } else {
              // When the file is a text file
            }
          }

        } else { // If the initial given path represents a textfile
          if (redirect)
            output = output + allArgs[i] + "\n";
          else
            stdout.print(allArgs[i]);
          // Printing the path name if the specified path represents a file
        }
      } else {
        // Print invalid path error
      }
    }
    if (redirect) {
      Echo echoCmd = new Echo();
      String echoArgs[] =
          {output, allArgs[allArgs.length - 2], allArgs[allArgs.length - 1]};
      echoCmd.execute(echoArgs);
    }
  }

}
