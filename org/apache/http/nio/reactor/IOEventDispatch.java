package org.apache.http.nio.reactor;

public interface IOEventDispatch {
  public static final String CONNECTION_KEY = "http.connection";
  
  void connected(IOSession paramIOSession);
  
  void inputReady(IOSession paramIOSession);
  
  void outputReady(IOSession paramIOSession);
  
  void timeout(IOSession paramIOSession);
  
  void disconnected(IOSession paramIOSession);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/IOEventDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */