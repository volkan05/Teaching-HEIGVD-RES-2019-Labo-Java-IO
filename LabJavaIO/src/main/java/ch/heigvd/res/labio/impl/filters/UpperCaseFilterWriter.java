package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti, Volkan Sutcu
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
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
      // The single character is automatically uppercased
      super.write(Character.toUpperCase(c));
  }

}
