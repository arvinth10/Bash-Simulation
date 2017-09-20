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

import a2.FileSystem;
import a2.Handler;
import a2.Mkdir;
import a2.Pushd;

/**
 * JUnit tests for  Pushd.java
 * 
 * @author Arvinth
 *
 */

public class PushdTest {
  /**
  * Pushd object being tested
  */
 private Pushd pushCommand;

 /**
  * Mkdir object used to assist in testing
  */
 private Mkdir mkdirCommand;
 private Handler handler;
 private FileSystem fileSystem;
 
 /**
  * setUp method used to set up for all test cases and reduce repeated code
  * 
  * @param none
  * @return none
  */
 @Before
 public void setUp() {
   FileSystem.createFileSystem(); // Creates the file system
   mkdirCommand = new Mkdir();
   pushCommand = new Pushd();
   handler = Handler.createHandler();
   fileSystem = FileSystem.createFileSystem();
 }

 /**
  * Test case for Pushd constructor
  * 
  * @param None
  * @return: None
  */
 @Test
 public void testCd() {
   assertNotNull(pushCommand);
   // If successfully created the object will not be null
 }

 /**
  * Test case for Pushd execute method
  * 
  * @param None
  * @return None
  */
 @Test
 public void testExecute() {
   String allArgs[] = {"test"};
   mkdirCommand.execute(allArgs);
   pushCommand.execute(allArgs);



   //Test 1: Check if the current working directory is changed to the one give in argument
   assertEquals(handler.getCurrentPath(), "/test"); 

   handler.popPath();
   // Test 2: Check if the current working directory is being pushed onto the stack
   assertEquals (handler.getCurrentPath(), "/");
 }

}
