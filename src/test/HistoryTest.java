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

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import a2.FileSystem;
import a2.History;

/**
 * JUnit tests for  History.java
 * 
 * @author Mathusan
 *
 */
public class HistoryTest {

  /**
   * History object being tested 
   */
  private History historyCmd;

  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */
  @Before
  public void setUp() {
    historyCmd = History.createHistory();
    FileSystem.createFileSystem();
    // Creating the file system
  }

  /**
   * Test case for History constructor
   * 
   * @return: none
   */
  @Test
  public void testHistory() {
    assertNotNull(historyCmd);
  }

  /**
   * Test case for AddToHistory method inside history class
   * 
   * @return: none
   */
  @Test
  public void testAddToHistory() {
    historyCmd.addToHistory("line1");
    historyCmd.addToHistory("line2");
    historyCmd.addToHistory("line3");

    ArrayList<String> test = new ArrayList<String>();
    test = historyCmd.getHistory();

    // Test 1
    assertEquals("line1", test.get(0));
    assertEquals("line2", test.get(1));
    assertEquals("line3", test.get(2));
  }

  /**
   * Test case for getHistory method inside history class
   * 
   * @return: none
   */
  @Test
  public void testGetHistory() {
    ArrayList<String> test = new ArrayList<String>();

    historyCmd.addToHistory("line1");
    historyCmd.addToHistory("line2");
    historyCmd.addToHistory("line3");

    test = historyCmd.getHistory();

    // Test 1
    assertEquals("line1", test.get(0));
    assertEquals("line2", test.get(1));
    assertEquals("line3", test.get(2));
    assertEquals(3, test.size());

  }

  /**
   * Test case for execute method inside history class
   * 
   * @return: none
   */
  @Test
  public void testExecute() {

    historyCmd.addToHistory("line1");
    historyCmd.addToHistory("line2");
    historyCmd.addToHistory("line3");

    ArrayList<String> test = new ArrayList<String>();
    String allArgs[] = {};
    test = historyCmd.helpExecute(allArgs);

    // Test 1
    assertEquals(3, test.size());

    String allArgs2[] = {"2"};
    test = historyCmd.helpExecute(allArgs2);

    // Test 2
    assertEquals(2, test.size());
    assertEquals("line2", test.get(0));
    assertEquals("line3", test.get(1));

    // Test3
    String allArgs3[] = {"9"};
    test = historyCmd.helpExecute(allArgs3);
    assertNull(test);

  }

}
