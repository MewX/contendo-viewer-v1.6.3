package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

public interface SequentialRead extends Closeable {
  int read() throws IOException;
  
  int read(byte[] paramArrayOfbyte) throws IOException;
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/SequentialRead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */