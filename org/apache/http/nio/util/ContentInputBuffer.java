package org.apache.http.nio.util;

import java.io.IOException;
import org.apache.http.nio.ContentDecoder;

public interface ContentInputBuffer {
  @Deprecated
  int consumeContent(ContentDecoder paramContentDecoder) throws IOException;
  
  void reset();
  
  int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  int read() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/ContentInputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */