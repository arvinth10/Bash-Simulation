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
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import a2.FileSystem;
import a2.Handler;
import a2.Pwd;

/**
 * JUnit tests for  Pwd.java
 * 
 * @author Mathusan
 *
 */
public class PwdTest {

  /** 
   * Pwd object that is being tested 
   */
  private Pwd pwdCommand;
  private Handler handler;
  private FileSystem fileSystem;
  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */

  @Before
  public void setUp() {
    pwdCommand = new Pwd();
    FileSystem.createFileSystem();
    handler = Handler.createHandler();
    fileSystem = FileSystem.createFileSystem();
  }


  /**
   * Test case for pwd constructor
   * 
   * @return: none
   */
  @Test
  public void testPwd() {
    assertNotNull(pwdCommand);
  }

  /**
   * Test case for pwd execute method
   * 
   * @return: none
   */
  @Test
  public void testExecute() {
    // Asumming printing is fine, we will check if the current working directory
    // Is updated correctly, since pwd only prints that

    assertEquals("/", handler.getCurrentPath());

  }

}
