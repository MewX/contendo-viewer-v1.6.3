package org.apache.http.nio;

import org.apache.http.HttpConnection;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

public interface NHttpConnection extends HttpConnection, IOControl {
  public static final int ACTIVE = 0;
  
  public static final int CLOSING = 1;
  
  public static final int CLOSED = 2;
  
  int getStatus();
  
  HttpRequest getHttpRequest();
  
  HttpResponse getHttpResponse();
  
  HttpContext getContext();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */