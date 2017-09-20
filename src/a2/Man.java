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
 * Man class is responsible for giving a manual for a command that user
 * requests, and should collaborate with the stdout class to print the manual.
 * 
 * @author Mathusan Selvarajah, Zaki Nabavi, Rixin Dai
 *
 */
public class Man extends Command {

  /**
   * Constructs a Man command object
   */
  public Man() {

  }

  /**
   * A method that executes the man command by providing a manual for the
   * desired command.
   * 
   * @path allArgs[]: A string array that is used to store argument passed along
   *       along with the man command.
   * @return: none
   */
  public void execute(String[] allArgs) {
    Stdout stdout = new Stdout();
    Boolean redirect;

    if (allArgs.length == 3) {
      redirect = true;
    } else {
      redirect = false;
    }

    if (allArgs[0].equals("mkdir")) {
      String str = "Command: mkdir DIR..."
          + "\n\n Create directories, each of which may be relative to the "
          + "current directory or may be a full path.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("cd")) {
      String str = " Command: Cd DIR "
          + "\n\n Change directory to DIR, which may be relative to the"
          + "\n current directory or may be a full path. As with Unix, .."
          + "\n means a parent directory and a . means the current directory."
          + "\n The directory must be /, the forward slash. "
          + "\n The foot of the :file system is a single slash: /.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    } else if (allArgs[0].equals("ls")) {
      String str = "Command: ls [Path...]"
          + "\n\n If no paths are given, print the contents (file or directory)"
          + "\n of the current directory, with a new line following each of"
          + " the "
          + "\n content (:ile or directory).Otherwise, for each path p, the"
          + " order" + "\n listed:" + "\n • If p speci:ies a :ile, print p"
          + "\n • If p speci:ies a directory, print p, a colon, then the "
          + "contents" + "\n of that directory, then an extra new line."
          + "\n Quit the program"
          + "\n • If p does not exist, print a suitable message.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("pwd")) {
      String str = "Command: pwd "
          + "\n\n Print the current working directory (including the"
          + " whole path).";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    } else if (allArgs[0].equals("pushd")) {
      String str = "Command: pushd Dir"
          + "\n\n Saves the current working directory by pushing onto directory"
          + "\n stack and then changes the new current working directory"
          + " to DIR. The"
          + "\n push must be consistent as per the LIFO behavior of"
          + " a stack. The "
          + "\n pushd command saves the old current working directory"
          + " in directory "
          + "\n stack so that it can be returned to at any time (via the popd"
          + " \n command). The size of the directory stack is dynamic and "
          + "\n dependent on the pushd and the  popd commands.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    } else if (allArgs[0].equals("popd")) {
      String str = "Command: popd"
          + "\n\n Remove the top entry from the directory stack, and cd into "
          + "\n it. The removal must be consistent as per the LIFO "
          + "behavior of a"
          + "\n stack. The popd command removes the top most directory"
          + " from the "
          + "\n directory stack and makes it the current working directory. "
          + "\n If there is no directory onto the stack, then give appropriate "
          + "\n error message.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    } else if (allArgs[0].equals("history")) {
      String str = "Command: history[number]"
          + "\n\n This command will print out recent commands, one command "
          + "per line. i.e.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }


    } else if (allArgs[0].equals("cat")) {
      String str = "Command: cat File1 [File2 ...]"
          + "\n\n Display the contents of FILE1 and other files "
          + "\n (i.e. File2 ....) concatenated in the shell."
          + " You may want to use"
          + "\n three line breaks to separate the contents of one file from the"
          + "\n other file.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    } else if (allArgs[0].equals("mv")) {
      String str = "Command: mv OLDPATH NEWPATH"
          + "\n\n Move item of OLDPATH to NEWPATH."
          + "\n If NEWPATH is a directory, move the item into the directory.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("cp")) {
      String str = "Command: cp OLDPATH NEWPATH"
          + "\n\n Copy item of OLDPATH to NEWPATH."
          + " If NEWPATH is a directory, paste the item into the directory.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("get")) {
      String str =
          "Command: get URL" + "\n\n  Retrieve the file at that URL and "
              + "add it to the current working directory.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("!")) {
      String str = "Command: !number"
          + "\n\n  Recall any of previous history by its number(>=1)  "
          + "preceded by an exclamation point (!).";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("grep")) {
      String str = "Command: grep [-R] REGEX PATH �"
          + "\n\n   print any lines containing REGEX in PATH.  "
          + "\n  If �R is supplied, and PATH is a directory, "
          + "\n recursively traverse the directory and, "
          + "\n for all lines in all files that contain REGEX"
          + "\n print the contents that meet the requirment ";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }
    } else if (allArgs[0].equals("echo")) {
      String str = "Command: echo STRING [>OUTFILE], echo STRING >> OUTFILE"
          + "\n\n If OUTFILE is not provided, print STRING on the shell. "
          + "\n Otherwise, put STRING into :File OUTFILE."
          + " STRING is a string of "
          + "\n characters surrounded by double quotation"
          + " marks. This creates a "
          + "\n new file if OUTFILE does not exists and"
          + "\n Remove the top entry from the directory stack, and cd into it. "
          + "\n The removal must be consistent as per the LIFO behavior of a "
          + "\n stack.  The popd command removes the top most directory from"
          + "\n the directory  stack and makes it the "
          + "current working directory."
          + "\n If there is no  directory onto the stack, then give appropriate"
          + "\n error message. erases the old contents if OUTFILE already"
          + "\n exists. In either case,  the only thing in OUTFILE should be "
          + " STRING.";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    } else if (allArgs[0].equals("man")) {
      String str =
          "Command: man CMD" + "\n\n Print documentation for a command (CMD)";
      if (redirect) {
        stdout.print(str, allArgs[1], allArgs[2]);
      } else {
        stdout.print(str);
      }

    }
  }
}


