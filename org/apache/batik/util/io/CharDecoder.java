package org.apache.batik.util.io;

import java.io.IOException;

public interface CharDecoder {
  public static final int END_OF_STREAM = -1;
  
  int readChar() throws IOException;
  
  void dispose() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/CharDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */