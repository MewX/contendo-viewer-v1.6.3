package org.apache.http.nio.reactor;

import java.io.IOException;

public interface IOReactorExceptionHandler {
  boolean handle(IOException paramIOException);
  
  boolean handle(RuntimeException paramRuntimeException);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/IOReactorExceptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */