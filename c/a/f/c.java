package c.a.f;

import java.io.IOException;

public interface c {
  void writeByte(int paramInt) throws IOException;
  
  void writeShort(int paramInt) throws IOException;
  
  void writeInt(int paramInt) throws IOException;
  
  void writeLong(long paramLong) throws IOException;
  
  void writeFloat(float paramFloat) throws IOException;
  
  void writeDouble(double paramDouble) throws IOException;
  
  int getByteOrdering();
  
  void flush() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/f/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */