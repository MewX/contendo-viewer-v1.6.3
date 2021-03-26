package org.apache.http;

import java.io.IOException;

public interface HttpServerConnection extends HttpConnection {
  HttpRequest receiveRequestHeader() throws HttpException, IOException;
  
  void receiveRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest) throws HttpException, IOException;
  
  void sendResponseHeader(HttpResponse paramHttpResponse) throws HttpException, IOException;
  
  void sendResponseEntity(HttpResponse paramHttpResponse) throws HttpException, IOException;
  
  void flush() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/HttpServerConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */