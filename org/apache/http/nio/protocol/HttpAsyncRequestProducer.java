package org.apache.http.nio.protocol;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.IOControl;
import org.apache.http.protocol.HttpContext;

public interface HttpAsyncRequestProducer extends Closeable {
  HttpHost getTarget();
  
  HttpRequest generateRequest() throws IOException, HttpException;
  
  void produceContent(ContentEncoder paramContentEncoder, IOControl paramIOControl) throws IOException;
  
  void requestCompleted(HttpContext paramHttpContext);
  
  void failed(Exception paramException);
  
  boolean isRepeatable();
  
  void resetRequest() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncRequestProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */