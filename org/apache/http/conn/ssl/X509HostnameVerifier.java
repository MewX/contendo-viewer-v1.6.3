package org.apache.http.conn.ssl;

import java.io.IOException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;

@Deprecated
public interface X509HostnameVerifier extends HostnameVerifier {
  void verify(String paramString, SSLSocket paramSSLSocket) throws IOException;
  
  void verify(String paramString, X509Certificate paramX509Certificate) throws SSLException;
  
  void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2) throws SSLException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/ssl/X509HostnameVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */