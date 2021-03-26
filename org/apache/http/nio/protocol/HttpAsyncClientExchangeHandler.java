package org.apache.http.nio.protocol;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.IOControl;

public interface HttpAsyncClientExchangeHandler extends Closeable, Cancellable {
  HttpRequest generateRequest() throws IOException, HttpException;
  
  void produceContent(ContentEncoder paramContentEncoder, IOControl paramIOControl) throws IOException;
  
  void requestCompleted();
  
  void responseReceived(HttpResponse paramHttpResponse) throws IOException, HttpException;
  
  void consumeContent(ContentDecoder paramContentDecoder, IOControl paramIOControl) throws IOException;
  
  void responseCompleted() throws IOException, HttpException;
  
  void inputTerminated();
  
  void failed(Exception paramException);
  
  boolean isDone();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncClientExchangeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */