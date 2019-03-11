package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti, Volkan Sutcu
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  // Initialised to 1 because counting lines in a text always starts to 1
  private int lineCpt = 1;
  // Detect separator combination
  private int previousChar = 0;

  // Separators applicated in a text
  private static final char NEWLINE = '\n';
  private static final char CARRIAGE_RETURN = '\r';
  private static final char TAB = '\t';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      // Convert str to an array of char and call the other write function
      write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      // Every char of the array is written as single char
      for(int i = off; i < off + len; ++i){
        write(cbuf[i]);
      }
  }

  @Override
  public void write(int c) throws IOException {
    final String LINE_NUMBER_CONVERTED = String.valueOf(lineCpt);
      // The number of line is written directly if it's the first line or we check the carriage return on MacOS
      if(lineCpt == 1 || c != NEWLINE && previousChar == CARRIAGE_RETURN){
        // Writes the number of line at the beginning of the text
        super.write(LINE_NUMBER_CONVERTED, 0, String.valueOf(lineCpt++).length());
        // A tabulation after every line number is added
        super.write(TAB);
      }

      // The charactere is simply written
      super.write(c);

      // Check for Unix or Windows carriage return
      if(c == NEWLINE){
        super.write(LINE_NUMBER_CONVERTED, 0, String.valueOf(lineCpt++).length());
        super.write(TAB);
      }
      // Remember the character for future verification
      previousChar = c;
  }

}
