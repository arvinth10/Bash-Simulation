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

import a2.TextFile;


/**
 * JUnit tests for  TextFile.java
 * 
 * @author Mathusan
 *
 */
public class TextFileTest {

  /**
   * Textfile object that is being tested
   */
  private TextFile file;

  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */
  @Before
  public void setUp() {
    file = new TextFile("newfile", "/newfile");
  }

  /**
   * Test case to test the TextFile constructor
   */
  @Test
  public void testTextFile() {
    assertNotNull(file);
  }

  /**
   * Test case to test SetContent method in TextFile class
   */
  @Test
  public void testSetContent() {
    file.setContent("newContent");
    assertEquals("newContent", file.getContents());
  }

  /**
   * Test case to test AppendToFile method in TextFile class
   */
  @Test
  public void testAppendToFile() {
    file.setContent("newContent");
    file.appendToFile("newerContent");
    assertEquals("newContent\nnewerContent", file.getContents());
  }

  /**
   * Test case to test getContents method in TextFile class
   */
  @Test
  public void testGetcontents() {
    file.setContent("newContent");
    String insideFile = file.getContents();

    assertEquals("newContent", insideFile);

  }

}
