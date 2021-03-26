package org.apache.http.nio.protocol;

import java.io.IOException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

@Deprecated
public interface HttpRequestExecutionHandler {
  void initalizeContext(HttpContext paramHttpContext, Object paramObject);
  
  HttpRequest submitRequest(HttpContext paramHttpContext);
  
  void handleResponse(HttpResponse paramHttpResponse, HttpContext paramHttpContext) throws IOException;
  
  void finalizeContext(HttpContext paramHttpContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpRequestExecutionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */