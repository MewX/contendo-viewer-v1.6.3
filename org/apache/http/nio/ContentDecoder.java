package org.apache.http.nio;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ContentDecoder {
  int read(ByteBuffer paramByteBuffer) throws IOException;
  
  boolean isCompleted();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/ContentDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */