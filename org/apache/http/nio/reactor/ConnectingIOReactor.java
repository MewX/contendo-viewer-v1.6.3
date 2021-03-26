package org.apache.http.nio.reactor;

import java.net.SocketAddress;

public interface ConnectingIOReactor extends IOReactor {
  SessionRequest connect(SocketAddress paramSocketAddress1, SocketAddress paramSocketAddress2, Object paramObject, SessionRequestCallback paramSessionRequestCallback);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/ConnectingIOReactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */