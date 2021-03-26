package org.apache.http.nio.protocol;

import org.apache.http.HttpRequest;

public interface HttpAsyncRequestHandlerMapper {
  HttpAsyncRequestHandler<?> lookup(HttpRequest paramHttpRequest);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequestHandlerMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */