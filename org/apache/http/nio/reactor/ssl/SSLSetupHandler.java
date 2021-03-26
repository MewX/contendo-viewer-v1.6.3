package org.apache.http.nio.reactor.ssl;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.apache.http.nio.reactor.IOSession;

public interface SSLSetupHandler {
  void initalize(SSLEngine paramSSLEngine) throws SSLException;
  
  void verify(IOSession paramIOSession, SSLSession paramSSLSession) throws SSLException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/ssl/SSLSetupHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */