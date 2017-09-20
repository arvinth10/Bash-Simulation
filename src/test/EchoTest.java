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

import a2.Echo;
import a2.FileSystem;
import a2.TextFile;

/**
 * JUnit tests for Echo.java
 * 
 * @author Arvinth
 *
 */

public class EchoTest {
  /**
   * echo object that is being tested 
   */
  private Echo echoCommand;
  /**
   * fileSystem object used to assist in testing
   */
  private FileSystem fileSystem;
  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param none
   * @return none
   */
  @Before
  public void setUp() {
    fileSystem = FileSystem.createFileSystem();
    // Creates the file system
    echoCommand = new Echo();
  }

  /**
   * Test case for Echo constructor
   * 
   * @param None
   * @return: None
   */
  @Test
  public void testEcho() {
    assertNotNull(echoCommand);
    // If successfully created the object will not be null
  }

  /**
   * Test case for Echo execute method
   * 
   * @param None
   * @return None
   */
  @Test
  public void testExecute() {
    String echoValues[] = {"\"echo\"", ">", "test.txt"};
    echoCommand.execute(echoValues);

    // Test 1: Check if the file is added to the file system
    assertEquals(fileSystem.getFiles().containsKey("/test.txt"), true);

    // Test 2: Checks if the file created in the file system has the correct
    // string
    assertEquals(
        ((TextFile) fileSystem.getFiles().get("/test.txt")).getContents(),
        "echo");

    echoValues[0] = "\"another echo\"";
    echoCommand.execute(echoValues);

    // Test 3: Check if the string get appended to the text file already created
    assertEquals(
        ((TextFile) fileSystem.getFiles().get("/test.txt")).getContents(),
        "echo\nanother echo");

    echoValues[1] = ">>";
    echoValues[0] = "No more echo!";
    echoCommand.execute(echoValues);

    // Test 4: Check if the string gets overwritten to the text file already
    // created
    assertEquals(
        ((TextFile) fileSystem.getFiles().get("/test.txt")).getContents(),
        "No more echo!");

    echoValues[2] = "test//test";

    // Test 5: Checks if invalid path names still creates a new text file
    assertEquals(fileSystem.getFiles().containsKey("test//test"), false);



  }


}
