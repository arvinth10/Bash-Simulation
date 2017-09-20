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

import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;

import a2.File;
import a2.FileSystem;
import a2.Mkdir;
/**
 * JUnit tests for  Mkdir.java
 * 
 * @author Mathusan
 *
 */
public class MkdirTest {
  /**
   * Mkdir object being tested
   */
  private Mkdir mkdirCmd;
  private FileSystem fileSystem;
  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */
  @Before
  public void setUp() {
    mkdirCmd = new Mkdir();
    fileSystem = FileSystem.createFileSystem();
    // Creating the file system
  }

  /**
   * Test case for mkdir execute method
   * 
   * @return: none
   */
  @Test
  public void testExecute() {
    String allArgs[] = {"testDir"};
    Hashtable<String, File> fileObjects = new Hashtable<String, File>();

    mkdirCmd.execute(allArgs);
    fileObjects = fileSystem.getFiles();

    // Test 1
    assertTrue(fileObjects.containsKey("/testDir"));

    String allArgs2[] = {"testDir", "testDir2", "/testDir/newDir"};
    mkdirCmd.execute(allArgs2);

    fileObjects = fileSystem.getFiles();

    // Test 2
    assertTrue(fileObjects.containsKey("/testDir"));
    assertTrue(fileObjects.containsKey("/testDir2"));
    assertTrue(fileObjects.containsKey("/testDir/newDir"));

  }

}
