package c.a.f;

import java.io.EOFException;
import java.io.IOException;

public interface b {
  byte readByte() throws EOFException, IOException;
  
  int readUnsignedByte() throws EOFException, IOException;
  
  short readShort() throws EOFException, IOException;
  
  int readUnsignedShort() throws EOFException, IOException;
  
  int readInt() throws EOFException, IOException;
  
  long readUnsignedInt() throws EOFException, IOException;
  
  long readLong() throws EOFException, IOException;
  
  float readFloat() throws EOFException, IOException;
  
  double readDouble() throws EOFException, IOException;
  
  int getByteOrdering();
  
  int skipBytes(int paramInt) throws EOFException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/f/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */