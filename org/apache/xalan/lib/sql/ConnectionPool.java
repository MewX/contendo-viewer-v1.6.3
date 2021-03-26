package org.apache.xalan.lib.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface ConnectionPool {
  boolean isEnabled();
  
  void setDriver(String paramString);
  
  void setURL(String paramString);
  
  void freeUnused();
  
  boolean hasActiveConnections();
  
  void setPassword(String paramString);
  
  void setUser(String paramString);
  
  void setMinConnections(int paramInt);
  
  boolean testConnection();
  
  Connection getConnection() throws SQLException;
  
  void releaseConnection(Connection paramConnection) throws SQLException;
  
  void releaseConnectionOnError(Connection paramConnection) throws SQLException;
  
  void setPoolEnabled(boolean paramBoolean);
  
  void setProtocol(Properties paramProperties);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/ConnectionPool.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */