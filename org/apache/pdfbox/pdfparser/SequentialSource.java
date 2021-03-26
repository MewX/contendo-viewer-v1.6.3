package org.apache.pdfbox.pdfparser;

import java.io.Closeable;
import java.io.IOException;

interface SequentialSource extends Closeable {
  int read() throws IOException;
  
  int read(byte[] paramArrayOfbyte) throws IOException;
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  long getPosition() throws IOException;
  
  int peek() throws IOException;
  
  void unread(int paramInt) throws IOException;
  
  void unread(byte[] paramArrayOfbyte) throws IOException;
  
  void unread(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  byte[] readFully(int paramInt) throws IOException;
  
  boolean isEOF() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/SequentialSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */