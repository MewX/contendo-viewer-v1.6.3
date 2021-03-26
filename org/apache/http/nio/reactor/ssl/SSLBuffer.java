package org.apache.http.nio.reactor.ssl;

import java.nio.ByteBuffer;

public interface SSLBuffer {
  ByteBuffer acquire();
  
  void release();
  
  boolean isAcquired();
  
  boolean hasData();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/ssl/SSLBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */