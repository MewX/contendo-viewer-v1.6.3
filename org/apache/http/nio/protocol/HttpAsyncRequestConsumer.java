package org.apache.http.nio.protocol;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;
import org.apache.http.protocol.HttpContext;

public interface HttpAsyncRequestConsumer<T> extends Closeable {
  void requestReceived(HttpRequest paramHttpRequest) throws HttpException, IOException;
  
  void consumeContent(ContentDecoder paramContentDecoder, IOControl paramIOControl) throws IOException;
  
  void requestCompleted(HttpContext paramHttpContext);
  
  void failed(Exception paramException);
  
  Exception getException();
  
  T getResult();
  
  boolean isDone();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequestConsumer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */