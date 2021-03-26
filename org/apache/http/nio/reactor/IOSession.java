package org.apache.http.nio.reactor;

import java.net.SocketAddress;
import java.nio.channels.ByteChannel;

public interface IOSession {
  public static final String ATTACHMENT_KEY = "http.session.attachment";
  
  public static final int ACTIVE = 0;
  
  public static final int CLOSING = 1;
  
  public static final int CLOSED = 2147483647;
  
  ByteChannel channel();
  
  SocketAddress getRemoteAddress();
  
  SocketAddress getLocalAddress();
  
  int getEventMask();
  
  void setEventMask(int paramInt);
  
  void setEvent(int paramInt);
  
  void clearEvent(int paramInt);
  
  void close();
  
  void shutdown();
  
  int getStatus();
  
  boolean isClosed();
  
  int getSocketTimeout();
  
  void setSocketTimeout(int paramInt);
  
  void setBufferStatus(SessionBufferStatus paramSessionBufferStatus);
  
  boolean hasBufferedInput();
  
  boolean hasBufferedOutput();
  
  void setAttribute(String paramString, Object paramObject);
  
  Object getAttribute(String paramString);
  
  Object removeAttribute(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/IOSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */