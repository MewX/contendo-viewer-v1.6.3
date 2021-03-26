package org.apache.http.conn;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpInetConnection;

public interface ManagedHttpClientConnection extends HttpClientConnection, HttpInetConnection {
  String getId();
  
  void bind(Socket paramSocket) throws IOException;
  
  Socket getSocket();
  
  SSLSession getSSLSession();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/ManagedHttpClientConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */