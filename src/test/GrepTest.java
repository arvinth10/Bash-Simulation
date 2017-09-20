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

import a2.Grep;
import a2.TextFile;
import a2.FileSystem;

/**
 * JUnits test for Grep.java
 * 
 * @author Arvinth
 *
 */
public class GrepTest {
  /**
   * grep object that is being tested
   */
  private Grep grepCommand;
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
  public void setUp() throws Exception {
    fileSystem = FileSystem.createFileSystem();
    grepCommand = new Grep();

  }


  /**
   * Test case for Grep constructor
   * 
   * @param None
   * @return: None
   */
  @Test
  public void testGrep() {
    assertNotNull(grepCommand);
  }

  /**
   * Test case for Grep execute method
   * 
   * @param None
   * @return None
   */
  @Test
  public void testExecute() {
    TextFile test = new TextFile("a.txt", "/a.txt");
    test.appendToFile("grepping this text");
    fileSystem.addFile("/a.txt", test);

    // Test 1: Test regular execute
    String grepValues[] = {"\"grep\"", "a.txt", ">", "b.txt"};
    grepCommand.execute(grepValues);
    assertEquals(((TextFile) fileSystem.getFiles().get("/b.txt")).getContents(),
        "grepping this text\n");

    // Test 2: Test -R execute
    String grepValues2[] = {"-r", "\"grep\"", "/", ">", "c.txt"};
    grepCommand.execute(grepValues2);
    assertEquals(((TextFile) fileSystem.getFiles().get("/c.txt")).getContents(),
        "a.txt: grepping this text\nb.txt: grepping this text\n");
  }

}
