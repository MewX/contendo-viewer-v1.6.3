package org.apache.http.nio.reactor;

import java.io.IOException;

public interface IOReactor {
  IOReactorStatus getStatus();
  
  void execute(IOEventDispatch paramIOEventDispatch) throws IOException;
  
  void shutdown(long paramLong) throws IOException;
  
  void shutdown() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/IOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */