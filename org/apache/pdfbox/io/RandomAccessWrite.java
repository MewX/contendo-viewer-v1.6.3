package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

public interface RandomAccessWrite extends Closeable {
  void write(int paramInt) throws IOException;
  
  void write(byte[] paramArrayOfbyte) throws IOException;
  
  void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  void clear() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessWrite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */