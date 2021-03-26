package org.apache.http.protocol;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

public interface HttpExpectationVerifier {
  void verify(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext) throws HttpException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/protocol/HttpExpectationVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */