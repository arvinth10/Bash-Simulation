// **********************************************************
// Assignment2B:

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

package a2;

/**
 * Keeps tracks of all information related to a particular text file in the file
 * system. Contains the content of the text file, name and path. Along with
 * method to change the context of the content in the text file.
 * 
 * @author Arvinth Vijayanathan
 *
 */
public class TextFile extends File {

  /**
   * A string of all the text files content
   */
  private String content = "";
  /**
   * The name of the text file
   */
  private String name = "";
  /**
   * The path where the text file is located
   */
  private String path = "";

  /**
   * Default Constructor
   * 
   * @param name Name of the text file
   * @param path Path where text file is located
   * @return None
   */
  public TextFile(String name, String path) {
    super(name, path);
  }

  /**
   * Set the content of the text file to given string, and overwrites the text
   * file if it already contains text
   * 
   * @param String content: Content to be written to the text file
   * @return None
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * A method that will add the given string content, to the string contents
   * that the file already contains
   * 
   * @param String newContent Contains text to be added to the file
   * @return None
   */
  public void appendToFile(String newContent) {
    content = content + "\n" + newContent;
  }


  /**
   * A getter method that will return the string contents of the file
   * 
   * @param None
   * @return String content : The content of the text file
   */
  public String getContents() {
    return content;
  }


}
