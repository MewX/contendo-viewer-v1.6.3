package org.apache.http.nio.protocol;

@Deprecated
public interface HttpAsyncRequestHandlerResolver {
  HttpAsyncRequestHandler<?> lookup(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequestHandlerResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */