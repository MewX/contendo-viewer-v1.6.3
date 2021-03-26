package org.apache.http.nio;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;

public interface NHttpServerConnection extends NHttpConnection {
  void submitResponse(HttpResponse paramHttpResponse) throws IOException, HttpException;
  
  boolean isResponseSubmitted();
  
  void resetInput();
  
  void resetOutput();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpServerConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */