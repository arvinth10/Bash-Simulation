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

import java.util.*;

/**
 * Stdout class is responsible for all non-error related output
 * 
 * @author Rixin Dai
 *
 */
public class Stdout {

  /**
   * Constructs an Stdout (Standard Output object) object Which is responsible
   * for all non-error related output
   */
  public Stdout() {

  }

  /**
   * Print method that just prints out the given string out to console
   * 
   * @param str: A string that needs to be printed to the console
   */
  public void print(String str) {
    System.out.println(str);
  }

  public void print(String str, String redirectType, String fileName) {
    Echo echocmd = new Echo();

    String echoArgs[] = {'"' + str + '"', redirectType, fileName};
    echocmd.execute(echoArgs);
    // Redirect the string output to a file with the given file name
  }

  /**
   * An overloaded print method that prints out an String ArryList that looks
   * like a numbered list.
   * 
   * @param ArrayList<String> list: An array list of strings that needs to be
   *        printed
   */
  public void print(ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println((i + 1) + ". " + list.get(i));
    }
  }

  public void print(ArrayList<String> list, String redirectType,
      String fileName) {
    String output = "";

    for (int i = 0; i < list.size(); i++) {
      output = output + (i + 1) + ". " + list.get(i) + "\n";
    }

    Echo echocmd = new Echo();

    String echoArgs[] = {'"' + output + '"', redirectType, fileName};
    echocmd.execute(echoArgs);
  }

  /**
   * Print method that prints out an String ArryList that looks like a numbered
   * list.
   * 
   * @param list: An array list of strings that needs to be printed
   * @param totalNum: The number of input that has been passed to History
   */


  public void print(ArrayList<String> list, int num) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println((i + 1 + num) + ". " + list.get(i));
    }
  }

  public void print(ArrayList<String> list, int num, String redirectType,
      String fileName) {
    String output = "";

    for (int i = 0; i < list.size(); i++) {
      output = output + (i + num + 1) + ". " + list.get(i) + "\n";
    }

    Echo echocmd = new Echo();

    String echoArgs[] = {'"' + output + '"', redirectType, fileName};
    echocmd.execute(echoArgs);
  }

}
