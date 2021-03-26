package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

public interface RandomAccessRead extends Closeable {
  int read() throws IOException;
  
  int read(byte[] paramArrayOfbyte) throws IOException;
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  long getPosition() throws IOException;
  
  void seek(long paramLong) throws IOException;
  
  long length() throws IOException;
  
  boolean isClosed();
  
  int peek() throws IOException;
  
  void rewind(int paramInt) throws IOException;
  
  byte[] readFully(int paramInt) throws IOException;
  
  boolean isEOF() throws IOException;
  
  int available() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/io/RandomAccessRead.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */