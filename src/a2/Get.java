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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Get class is responsible for getting information from a given website, and
 * storing it in the current working directory
 * 
 * @author c5selvar
 *
 */

public class Get extends Command {

  /**
   * This method executes the get command by calling the getUrl method and
   * handles any errors it may give.
   *
   * @param allArgs: A String array that holds all arguments to the get Command
   *
   * @return: none
   */
  public void execute(String allArgs[]) {
    try {
      getUrl(allArgs[0]);
    } catch (Exception e) {
      e.printStackTrace(); // exception handling is already done in getUrl
    } // method. This is just for safety
  }

  /**
   * Method takes in a .html or .txt URL and retrieves the information held on
   * that webpage, and stores it in the current working directory
   * 
   * @param webUrl: The URL that the user wants to retrieve information from
   * @returns: none
   */
  private void getUrl(String webUrl) throws Exception {
    URL url;
    BufferedReader input = null;
    Stderr stderr = new Stderr();
    Boolean proceed = false;

    Echo echocmd = new Echo(); // Will be used to add/overwrite a file

    if (webUrl.endsWith(".txt") || webUrl.endsWith(".html")) {
      try {
        url = new URL(webUrl);
        input = new BufferedReader(new InputStreamReader(url.openStream()));
        proceed = true;
      } catch (MalformedURLException e) {
        stderr.print(12);
        proceed = false;
      } catch (IOException e) {
        stderr.print(12);
        proceed = false;
      }
    } else {
      stderr.print("get", 12);
    }

    if (proceed) { // If no exception was thrown when retrieving content
      String line;
      String contents = "";

      while ((line = input.readLine()) != null) {
        contents = contents + "\n" + line; // Concatenating all lines in
        // website to one string
      }
      input.close();

      String splitUrl[] = webUrl.split("/");
      String txtFileName = splitUrl[splitUrl.length - 1];


      String[] echoCmdArgs = {('"' + contents + '"'), ">>", txtFileName};
      echocmd.execute(echoCmdArgs);
      // This will create a text file or overwrite a text file with the given
      // Contents and file name
    }
  }

}
