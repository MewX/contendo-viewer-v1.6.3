package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

public interface LineFormatter {
  CharArrayBuffer appendProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ProtocolVersion paramProtocolVersion);
  
  CharArrayBuffer formatRequestLine(CharArrayBuffer paramCharArrayBuffer, RequestLine paramRequestLine);
  
  CharArrayBuffer formatStatusLine(CharArrayBuffer paramCharArrayBuffer, StatusLine paramStatusLine);
  
  CharArrayBuffer formatHeader(CharArrayBuffer paramCharArrayBuffer, Header paramHeader);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/message/LineFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */