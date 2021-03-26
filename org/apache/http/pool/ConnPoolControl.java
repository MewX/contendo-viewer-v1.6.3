package org.apache.http.pool;

public interface ConnPoolControl<T> {
  void setMaxTotal(int paramInt);
  
  int getMaxTotal();
  
  void setDefaultMaxPerRoute(int paramInt);
  
  int getDefaultMaxPerRoute();
  
  void setMaxPerRoute(T paramT, int paramInt);
  
  int getMaxPerRoute(T paramT);
  
  PoolStats getTotalStats();
  
  PoolStats getStats(T paramT);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/pool/ConnPoolControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */