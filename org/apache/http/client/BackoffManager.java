package org.apache.http.client;

import org.apache.http.conn.routing.HttpRoute;

public interface BackoffManager {
  void backOff(HttpRoute paramHttpRoute);
  
  void probe(HttpRoute paramHttpRoute);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/BackoffManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */