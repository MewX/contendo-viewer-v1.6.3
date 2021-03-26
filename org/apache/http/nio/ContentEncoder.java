package org.apache.http.nio;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ContentEncoder {
  int write(ByteBuffer paramByteBuffer) throws IOException;
  
  void complete() throws IOException;
  
  boolean isCompleted();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/ContentEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */