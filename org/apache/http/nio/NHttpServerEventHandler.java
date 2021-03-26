package org.apache.http.nio;

import java.io.IOException;
import org.apache.http.HttpException;

public interface NHttpServerEventHandler {
  void connected(NHttpServerConnection paramNHttpServerConnection) throws IOException, HttpException;
  
  void requestReceived(NHttpServerConnection paramNHttpServerConnection) throws IOException, HttpException;
  
  void inputReady(NHttpServerConnection paramNHttpServerConnection, ContentDecoder paramContentDecoder) throws IOException, HttpException;
  
  void responseReady(NHttpServerConnection paramNHttpServerConnection) throws IOException, HttpException;
  
  void outputReady(NHttpServerConnection paramNHttpServerConnection, ContentEncoder paramContentEncoder) throws IOException, HttpException;
  
  void endOfInput(NHttpServerConnection paramNHttpServerConnection) throws IOException;
  
  void timeout(NHttpServerConnection paramNHttpServerConnection) throws IOException;
  
  void closed(NHttpServerConnection paramNHttpServerConnection);
  
  void exception(NHttpServerConnection paramNHttpServerConnection, Exception paramException);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/NHttpServerEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */