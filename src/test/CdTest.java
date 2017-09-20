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

import a2.Cd;
import a2.FileSystem;
import a2.Handler;
import a2.Mkdir;

/**
 * JUnit tests for  Cd.java
 * 
 * @author Arvinth
 *
 */


public class CdTest {
  /**
   * Cd object being tested
   */
  private Cd cdCommand;
  
  /**
   * Mkdir object used to assist in testing 
   */
  private Mkdir mkdirCommand;
  private FileSystem fileSystem;
  private Handler handler = Handler.createHandler();
  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param none
   * @return none
   */
  @Before
  public void setUp(){
    fileSystem = FileSystem.createFileSystem(); // Creates the file system
    cdCommand = new Cd(); 
    mkdirCommand = new Mkdir(); 
  }
  
  /**
   * Test case for Cd constructor
   * 
   * @param None
   * @return: None
   */
  @Test
  public void testCd() {
    assertNotNull(cdCommand);
    // If successfully created the object will not be null
  }
  
  /**
   * Test case for Cd execute method
   * 
   * @param None
   * @return None
   */
  @Test
  public void testExecute() {
    String allArgs[]= {"test"};
    mkdirCommand.execute(allArgs);
    
    //Ensure file system started with root directory
    assertEquals(handler.getCurrentPath(), "/"); 
    
    
    cdCommand.execute(allArgs);
    //Test 1: Check if directory changed 
    assertEquals(handler.getCurrentPath(),"/test"); 
    
    allArgs[0] = "..";
    cdCommand.execute(allArgs);
    
    //Test 2: Check if .. only goes to parent directory
    assertEquals(handler.getCurrentPath(), "/");
 
    
    allArgs[0] = ".";
    cdCommand.execute(allArgs);
    
    //Test 3: Check if . only doesn't do anything to the current working directory
    assertEquals(handler.getCurrentPath(), "/"); 
    
    allArgs[0] = "/test/path"; 
    mkdirCommand.execute(allArgs);
    handler.setCurrentPath("/test/path");
    allArgs[0] = "../path";
    cdCommand.execute(allArgs);
    
    //Test 4: .. with a path after
    assertEquals(handler.getCurrentPath(), "/test/path");
    
    handler.setCurrentPath("/test");
    allArgs[0]= "./path"; 
    cdCommand.execute(allArgs);
    
    //Test 5 : . with a path after 
    assertEquals(handler.getCurrentPath(), "/test/path"); 
    
    handler.setCurrentPath("/");
    allArgs[0] = "/test/path"; 
    cdCommand.execute(allArgs);
    
    //Test 5: Change current working directory using a full path
    assertEquals (handler.getCurrentPath(), "/test/path"); 
  }

}
