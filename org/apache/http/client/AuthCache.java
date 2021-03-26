package org.apache.http.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;

public interface AuthCache {
  void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme);
  
  AuthScheme get(HttpHost paramHttpHost);
  
  void remove(HttpHost paramHttpHost);
  
  void clear();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/AuthCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */