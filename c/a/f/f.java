package c.a.f;

import java.io.EOFException;
import java.io.IOException;

public interface f extends b, c {
  void close() throws IOException;
  
  int getPos() throws IOException;
  
  int length() throws IOException;
  
  void seek(int paramInt) throws IOException;
  
  int read() throws EOFException, IOException;
  
  void readFully(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  void write(int paramInt) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/f/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */