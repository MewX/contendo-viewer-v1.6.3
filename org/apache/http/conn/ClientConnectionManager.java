package org.apache.http.conn;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;

@Deprecated
public interface ClientConnectionManager {
  SchemeRegistry getSchemeRegistry();
  
  ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject);
  
  void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit);
  
  void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit);
  
  void closeExpiredConnections();
  
  void shutdown();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/ClientConnectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */