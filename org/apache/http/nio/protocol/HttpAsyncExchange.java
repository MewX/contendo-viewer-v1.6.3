package org.apache.http.nio.protocol;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.Cancellable;

public interface HttpAsyncExchange {
  HttpRequest getRequest();
  
  HttpResponse getResponse();
  
  void submitResponse();
  
  void submitResponse(HttpAsyncResponseProducer paramHttpAsyncResponseProducer);
  
  boolean isCompleted();
  
  void setCallback(Cancellable paramCancellable);
  
  void setTimeout(int paramInt);
  
  int getTimeout();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncExchange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */