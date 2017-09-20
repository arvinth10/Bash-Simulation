// **********************************************************
// Assignment2A:

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

package driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

import a2.Cat;
import a2.Cd;
import a2.Command;
import a2.Copy;
import a2.Directory;
import a2.Echo;
import a2.FileSystem;
import a2.Get;
import a2.Grep;
import a2.History;
import a2.Ls;
import a2.Man;
import a2.Mkdir;
import a2.Move;
import a2.Popd;
import a2.Pushd;
import a2.Pwd;
import a2.Stderr;
import a2.Validator;
import a2.recall;

/**
 * JShell class is responsible for getting user input and is responsible for
 * collaborating with other command classes to execute those inputs.
 * 
 * @Author: Mathusan Selvarajah, Rixin Dai, Zaki Nabavi
 */
public class JShell {
  /**
   * Everything JShell is responsible for, is done in this main method. JShell
   * is pretty much a driver for this program
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    // Creating all the commands

    Hashtable<String, Command> allCommands = new Hashtable<String, Command>();

    // Adding all command instances to a hashtable
    allCommands.put("cat", new Cat());
    allCommands.put("cd", new Cd());
    allCommands.put("echo", new Echo());
    allCommands.put("history", History.createHistory());
    allCommands.put("ls", new Ls());
    allCommands.put("mkdir", new Mkdir());
    allCommands.put("popd", new Popd());
    allCommands.put("pushd", new Pushd());
    allCommands.put("pwd", new Pwd());
    allCommands.put("man", new Man());
    allCommands.put("!", new recall());
    allCommands.put("mv", new Move());
    allCommands.put("cp", new Copy());
    allCommands.put("get", new Get());
    allCommands.put("grep", new Grep());
    
    Scanner in = new Scanner(System.in);

    FileSystem.createFileSystem();

    while (true) {
      System.out.print("/# ");

      String input = in.nextLine();
      input = input.trim();
      ((History) allCommands.get("history")).addToHistory(input); // Downcasting

      String commandCheck[] = input.split("\\s+");
      String tokens[];
      
      if (commandCheck[0].equals("echo")) {
        tokens = new String[4];
        tokens[0] = commandCheck[0];
        Stderr stderr = new Stderr();
        if (input.length() - input.replaceAll("\"", "").length() == 2) {
          String echoTokens[] = input.split("\""); // Splitting by quotation
                                                   // marks
          if (echoTokens.length >= 3) {
            tokens = new String[4];
            String tempTokens[] = echoTokens[2].split("\\s+"); // tempTokens
                                                               // used as temp
                                                               // variable here
            tokens[2] = tempTokens[1];
            tokens[3] = tempTokens[2];
          } else {
            tokens = new String[2];
          }

          tokens[0] = commandCheck[0];
          tokens[1] = "\"" + echoTokens[1] + "\"";

        } else {
          stderr.print(1);

        }
      } else {
        tokens = input.split("\\s+");
      }
      Validator validator = new Validator();
      if (!((tokens.length == 1) && (tokens[0].equals("")))) {
        if (validator.checkCMD(tokens) == true) {

          if (tokens[0].equals("exit")) {
            break; // end program if input is exit
          }else if (tokens[0].charAt(0) == '!'){
            //If entered command was like: !2
            allCommands.get("!").execute(tokens);
          }else{

          String allArgs[] = Arrays.copyOfRange(tokens, 1, tokens.length);
          // Get the arguments of the input entered.
          // So everything but command name
          allCommands.get(tokens[0]).execute(allArgs);
          }
        }
      }

    }

    in.close();
  }
}
