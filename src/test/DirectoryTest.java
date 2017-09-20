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

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;

import a2.Directory;
import a2.File;
import a2.FileSystem;


/**
 * JUnit tests for  Directory.java
 * 
 * @author Mathusan
 *
 */

public class DirectoryTest {
  
  /**
   * Directory object being tested 
   */
  private Directory dir;

  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */
  @Before
  public void setUp() {
    dir = new Directory("dir", "/dir");
    FileSystem fileSystem = FileSystem.createFileSystem();
    fileSystem.addFile("/dir", dir);
  }

  /**
   * Test case to Check if the Directory constructor
   */
  @Test
  public void testDirectory() {
    assertNotNull(dir);
  }


  /**
   * Test case to check getContents method in Directory class
   */
  @Test
  public void testGetContents() {
    FileSystem fileSystem = FileSystem.createFileSystem();
    fileSystem.addFile("/dir/childDir1", new Directory ("childDir1", "/dir/childDir1"));
    fileSystem.addFile("/dir/childDir2", new Directory ("childDir2", "/dir/childDir2"));


    ArrayList<String> contents = new ArrayList<String>();
    contents = dir.getContents();
    
    assertEquals(2, contents.size());
    assertEquals("/dir/childDir1", contents.get(0));
    assertEquals("/dir/childDir2", contents.get(1));
  }

}
