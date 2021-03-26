package org.apache.http.client;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public interface RedirectStrategy {
  boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext) throws ProtocolException;
  
  HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext) throws ProtocolException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/client/RedirectStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */