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
import a2.Validator;

/**
 * JUnit tests for  ValidatorTest.java
 * 
 * @author Mathusan
 *
 */
public class ValidatorTest {

  /**
   * Validator object that is being tested
   */
  private Validator validatorCmd;

  /**
   * setUp method used to set up for all test cases and reduce repeated code
   * 
   * @param: none
   */
  @Before
  public void setUp() {
    validatorCmd = new Validator();
    FileSystem.createFileSystem();
    // Creating the file system
  }


  /**
   * Test case for Validator constructor
   * 
   * @return: none
   */
  @Test
  public void testValidator() {
    assertNotNull(validatorCmd);
  }

  /**
   * Test case for CheckCMD method
   * 
   * @return: none
   */
  @Test
  public void testCheckCMD() {

    // Test 1
    String allArgs[] = {"echo", "SoemString", ">", "somefile"};
    assertTrue(validatorCmd.checkCMD(allArgs));

    // Test 2
    String allArgs2[] = {"echo", "SoemString", ">>>", "somefile"};
    assertFalse(validatorCmd.checkCMD(allArgs2));

    // Test 3
    String allArgs3[] = {"mkdir"};
    assertFalse(validatorCmd.checkCMD(allArgs3));

    // Test 4
    String allArgs4[] = {"aoisfjs"};
    assertFalse(validatorCmd.checkCMD(allArgs4));

    // Test 5
    String allArgs5[] = {"pwd"};
    assertTrue(validatorCmd.checkCMD(allArgs5));

    // Test 6
    String allArgs6[] = {"pushd"};
    assertFalse(validatorCmd.checkCMD(allArgs6));

  }

}
