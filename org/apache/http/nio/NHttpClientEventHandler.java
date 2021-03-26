package org.apache.http.nio;

import java.io.IOException;
import org.apache.http.HttpException;

public interface NHttpClientEventHandler {
  void connected(NHttpClientConnection paramNHttpClientConnection, Object paramObject) throws IOException, HttpException;
  
  void requestReady(NHttpClientConnection paramNHttpClientConnection) throws IOException, HttpException;
  
  void responseReceived(NHttpClientConnection paramNHttpClientConnection) throws IOException, HttpException;
  
  void inputReady(NHttpClientConnection paramNHttpClientConnection, ContentDecoder paramContentDecoder) throws IOException, HttpException;
  
  void outputReady(NHttpClientConnection paramNHttpClientConnection, ContentEncoder paramContentEncoder) throws IOException, HttpException;
  
  void endOfInput(NHttpClientConnection paramNHttpClientConnection) throws IOException;
  
  void timeout(NHttpClientConnection paramNHttpClientConnection) throws IOException, HttpException;
  
  void closed(NHttpClientConnection paramNHttpClientConnection);
  
  void exception(NHttpClientConnection paramNHttpClientConnection, Exception paramException);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpClientEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */