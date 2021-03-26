package org.apache.http.nio.reactor;

public interface SessionRequestCallback {
  void completed(SessionRequest paramSessionRequest);
  
  void failed(SessionRequest paramSessionRequest);
  
  void timeout(SessionRequest paramSessionRequest);
  
  void cancelled(SessionRequest paramSessionRequest);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/SessionRequestCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */