package org.apache.http.nio.protocol;

import java.io.IOException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.nio.entity.ConsumingNHttpEntity;
import org.apache.http.protocol.HttpContext;

@Deprecated
public interface NHttpRequestHandler {
  ConsumingNHttpEntity entityRequest(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest, HttpContext paramHttpContext) throws HttpException, IOException;
  
  void handle(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, NHttpResponseTrigger paramNHttpResponseTrigger, HttpContext paramHttpContext) throws HttpException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/NHttpRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */