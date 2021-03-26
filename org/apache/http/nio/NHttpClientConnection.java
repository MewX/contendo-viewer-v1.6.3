package org.apache.http.nio;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;

public interface NHttpClientConnection extends NHttpConnection {
  void submitRequest(HttpRequest paramHttpRequest) throws IOException, HttpException;
  
  boolean isRequestSubmitted();
  
  void resetOutput();
  
  void resetInput();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpClientConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */