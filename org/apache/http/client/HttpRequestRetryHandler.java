package org.apache.http.client;

import java.io.IOException;
import org.apache.http.protocol.HttpContext;

public interface HttpRequestRetryHandler {
  boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/HttpRequestRetryHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */