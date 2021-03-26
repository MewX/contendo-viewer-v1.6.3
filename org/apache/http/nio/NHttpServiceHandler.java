package org.apache.http.nio;

import java.io.IOException;
import org.apache.http.HttpException;

@Deprecated
public interface NHttpServiceHandler {
  void connected(NHttpServerConnection paramNHttpServerConnection);
  
  void requestReceived(NHttpServerConnection paramNHttpServerConnection);
  
  void inputReady(NHttpServerConnection paramNHttpServerConnection, ContentDecoder paramContentDecoder);
  
  void responseReady(NHttpServerConnection paramNHttpServerConnection);
  
  void outputReady(NHttpServerConnection paramNHttpServerConnection, ContentEncoder paramContentEncoder);
  
  void exception(NHttpServerConnection paramNHttpServerConnection, IOException paramIOException);
  
  void exception(NHttpServerConnection paramNHttpServerConnection, HttpException paramHttpException);
  
  void timeout(NHttpServerConnection paramNHttpServerConnection);
  
  void closed(NHttpServerConnection paramNHttpServerConnection);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpServiceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */