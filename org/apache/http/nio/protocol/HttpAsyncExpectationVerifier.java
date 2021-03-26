package org.apache.http.nio.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.protocol.HttpContext;

public interface HttpAsyncExpectationVerifier {
  void verify(HttpAsyncExchange paramHttpAsyncExchange, HttpContext paramHttpContext) throws HttpException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/protocol/HttpAsyncExpectationVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */