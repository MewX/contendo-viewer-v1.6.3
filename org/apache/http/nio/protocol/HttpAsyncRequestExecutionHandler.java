package org.apache.http.nio.protocol;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;

@Deprecated
public interface HttpAsyncRequestExecutionHandler<T> extends HttpAsyncRequestProducer, HttpAsyncResponseConsumer<T> {
  HttpContext getContext();
  
  HttpProcessor getHttpProcessor();
  
  ConnectionReuseStrategy getConnectionReuseStrategy();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequestExecutionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */