package org.apache.http.auth;

import org.apache.http.protocol.HttpContext;

public interface AuthSchemeProvider {
  AuthScheme create(HttpContext paramHttpContext);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/auth/AuthSchemeProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */