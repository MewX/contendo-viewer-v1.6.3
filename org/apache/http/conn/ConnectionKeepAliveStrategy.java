package org.apache.http.conn;

import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

public interface ConnectionKeepAliveStrategy {
  long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/ConnectionKeepAliveStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */