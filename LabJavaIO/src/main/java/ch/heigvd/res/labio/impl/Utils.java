package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti, Volkan Sutcu
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  // Represent the number of entry in the array when en line is separated
  private static final int NUMBER_OF_LINE = 2;

  // Allows to check the system used
  private static final String MAC = "\r";
  private static final String UNIX = "\n";
  private static final String WINDOWS = "\r\n";

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
      // Manage the lines related to the system used
      String[] resultNextLine = new String[NUMBER_OF_LINE];
      String[] separators = {MAC, UNIX, WINDOWS};
      boolean isSeparator = false;

      // Identify the system used
      for(int i = 0; i < separators.length; ++i){
          // Detect the separator
        if(lines.contains(separators[i])){
            // Split one line to two lines
          resultNextLine = lines.split(separators[i], NUMBER_OF_LINE);
          resultNextLine[0] += separators[i];
          isSeparator = true;
        }
      }

      // Allows to manage a line without separator
      if(!isSeparator) {
          resultNextLine[0] = "";
          resultNextLine[1] = lines;
      }

      return resultNextLine;
  }
}
