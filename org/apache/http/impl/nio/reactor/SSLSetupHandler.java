package org.apache.http.impl.nio.reactor;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.apache.http.nio.reactor.IOSession;
import org.apache.http.params.HttpParams;

@Deprecated
public interface SSLSetupHandler {
  void initalize(SSLEngine paramSSLEngine, HttpParams paramHttpParams) throws SSLException;
  
  void verify(IOSession paramIOSession, SSLSession paramSSLSession) throws SSLException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SSLSetupHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */