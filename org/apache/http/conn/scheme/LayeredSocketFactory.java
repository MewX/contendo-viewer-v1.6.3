package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
public interface LayeredSocketFactory extends SocketFactory {
  Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException, UnknownHostException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/scheme/LayeredSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */