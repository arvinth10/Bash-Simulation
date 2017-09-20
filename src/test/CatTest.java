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

import a2.Cat;
import a2.Echo;
import a2.FileSystem;

/**
 * JUnit tests for  Cat.java
 * 
 * @author Mathusan
 *
 */

public class CatTest {
  
  /**
   * Cat object being tested
   */
  private Cat catCommand;
  
  /**
   * Echo object used to assist in testing
   */
  private Echo echoCommand;

  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */
  @Before
  public void setUp() {
    catCommand = new Cat();
    echoCommand = new Echo();
  }

  /**
   * Test case for Cat constructor
   * 
   * @return: none
   */
  @Test
  public void testCat() {
    assertNotNull(catCommand);
    // If the cat object was successfully created, then it would not be null
  }

  /**
   * Test case for Cat execute method
   * 
   * @return: none
   */
  @Test
  public void testExecute() {

    FileSystem.createFileSystem();
    // Creating the file system

    String echoValues[] = {"\"somecontent\"", ">", "testfile"};
    echoCommand.execute(echoValues);
    // Adding the file to the system.

    String catValues[] = {"testfile"};
    String output = catCommand.helpExecute(catValues);

    // Test1
    assertEquals("somecontent\n\n\n\n", output);


    String echoValues2[] = {"\"somecontent2\"", ">", "testfile2"};
    echoCommand.execute(echoValues2);
    String catValues2[] = {"testfile", "testfile2"};
    String output2 = catCommand.helpExecute(catValues2);

    // Test2: Check for cat command with multiple paths
    assertEquals("somecontent\n\n\n\nsomecontent2\n\n\n\n", output2);

    String catValues3[] = {"testfile3333"};

    // Test3: Check if it returns something for an invalid command
    assertNull(catCommand.helpExecute(catValues3));

    String catValues4[] = {"testfile", "testfile3333", "testfile2"};
    String output3 = catCommand.helpExecute(catValues4);

    // Test4: Check if it prints all valid commands paths only
    assertEquals("somecontent\n\n\n\nsomecontent2\n\n\n\n", output3);

  }
}
